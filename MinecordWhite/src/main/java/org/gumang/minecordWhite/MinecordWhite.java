package org.gumang.minecordWhite;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.gumang.minecordWhite.Command.mweCommand;
import org.gumang.minecordWhite.Discord.DiscordChatListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class MinecordWhite extends JavaPlugin {

    private JDA jda;
    private String token;
    private File logFile;

    @Override
    public void onEnable() {
        // 기본 config.yml 파일 저장
        saveDefaultConfig();

        loadToken();

        if (token == null || token.isEmpty()) {
            getLogger().severe("Discord 토큰이 설정되지 않았습니다. config.yml을 확인하세요.");
            return;
        }

        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "whitelist on");

        startBot(token); // 봇 시작

        getCommand("mwe").setExecutor(new mweCommand(this)); // mwe 명령어 설정

        // command_log.txt 파일 초기화
        logFile = new File(getDataFolder(), "command_log.txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                getLogger().severe("로그 파일을 생성할 수 없습니다: " + e.getMessage());
            }
        }
    }

    @Override
    public void onDisable() {
        stopBot(); // 봇 종료
    }

    public void loadToken() {
        token = getConfig().getString("discord.token");
    }

    // 봇을 시작하는 메서드
    public void startBot(String token) {
        try {
            loadToken();

            JDABuilder builder = JDABuilder.createDefault(token);
            builder.setActivity(Activity.playing("화이트리스트 추가"));
            builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
            builder.addEventListeners(new DiscordChatListener(this)); // Add listeners again


            jda = builder.build();
            getLogger().info("디스코드 봇이 성공적으로 실행되었습니다.");
        } catch (Exception e) {
            getLogger().severe("디스코드 봇 초기화에 실패했습니다. 오류 메시지: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // 봇을 종료하는 메서드
    public void stopBot() {
        if (jda != null) {
            jda.shutdownNow();
            getLogger().info("디스코드 봇이 종료되었습니다.");
        }
    }

    // JDA 객체를 반환하는 메서드
    public JDA getJda() {
        return jda;
    }

    // 토큰을 반환하는 메서드
    public String getToken() {
        return token;
    }

    // user.yml 파일 경로 동적 처리
    public Path getUserFilePath() {
        return Paths.get(getDataFolder().toPath().toString(), "user.yml");
    }

    // 명령어 로그 기록 메서드
    public void logCommandToFile(String discordUsername, String playerName, String result) {
        try (FileWriter writer = new FileWriter(logFile, true)) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = now.format(formatter);

            writer.write(String.format("[%s] [%s] 닉네임: %s, 결과: %s%n", formattedDate, discordUsername, playerName, result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
