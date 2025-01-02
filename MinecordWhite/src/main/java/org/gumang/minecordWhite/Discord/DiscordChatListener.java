package org.gumang.minecordWhite.Discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.bukkit.Bukkit;
import org.gumang.minecordWhite.MinecordWhite;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DiscordChatListener extends ListenerAdapter {

    private final MinecordWhite plugin;

    public DiscordChatListener(MinecordWhite plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        event.getJDA().upsertCommand("화리", "마인크래프트 서버 화이트리스트에 닉네임을 추가합니다.")
                .addOption(OptionType.STRING, "닉네임", "마인크래프트 플레이어의 닉네임", true)
                .queue();
        Bukkit.getLogger().info("`/화리` 슬래시 명령어가 성공적으로 등록되었습니다.");
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("화리")) {

            String discordUsername = event.getUser().getAsTag(); // 디스코드 사용자명
            String playerName = event.getOption("닉네임").getAsString();

            // `moreAdd` 설정 읽기
            boolean moreAdd = plugin.getConfig().getBoolean("moreAdd");

            // 비동기에서 메인 스레드로 작업 전환
            Bukkit.getScheduler().runTask(plugin, () -> {
                String result;

                // user.yml 파일 경로 얻기
                Path userFilePath = plugin.getUserFilePath().toFile().toPath();
                File userFile = userFilePath.toFile();
                org.bukkit.configuration.file.FileConfiguration userConfig = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(userFile);

                if (!moreAdd && userConfig.contains(discordUsername)) {
                    // `moreAdd`가 false이고 이미 등록된 사용자일 경우
                    result = "이미 화이트리스트에 추가 했었습니다. 새로운 유저를 추가하려면 `moreAdd`를 활성화해야 합니다.";

                    // 임베드 메시지 생성
                    String formattedDate = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    EmbedBuilder embed = new EmbedBuilder()
                            .setTitle("화이트리스트 추가 " + playerName)
                            .setDescription(result)
                            .setColor(0xFF0000) // Yellow color for already added users
                            .setFooter("일시: " + formattedDate);

                    // 메시지 전송
                    event.replyEmbeds(embed.build()).queue();

                    // 로그 기록
                    plugin.logCommandToFile(discordUsername, playerName, result);
                } else {
                    // 화이트리스트 추가
                    boolean success = Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "whitelist add " + playerName);

                    String formattedDate = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                    if (success) {
                        result = "화이트리스트에 성공적으로 추가되었습니다.";

                        // 유저 데이터 추가
                        List<String> addedNames = userConfig.getStringList(discordUsername + ".add");
                        if (!addedNames.contains(playerName)) {
                            addedNames.add(playerName); // 중복 방지
                            userConfig.set(discordUsername + ".add", addedNames);
                            saveUserFile(userFile, userConfig); // user.yml 저장
                        }

                        plugin.logCommandToFile(discordUsername, playerName, result);
                    } else {
                        result = "화이트리스트에 추가되지 않았습니다.";
                    }

                    // 임베드 메시지 생성
                    EmbedBuilder embed = new EmbedBuilder()
                            .setTitle("화이트리스트 추가 " + playerName)
                            .setDescription(result)
                            .setColor(success ? 0x00FF00 : 0xFF0000) // Green for success, Red for failure
                            .setFooter("일시: " + formattedDate);

                    // 메시지 전송
                    event.replyEmbeds(embed.build()).queue();
                }
            });
        }
    }

    private void saveUserFile(File userFile, org.bukkit.configuration.file.FileConfiguration userConfig) {
        try {
            userConfig.save(userFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
