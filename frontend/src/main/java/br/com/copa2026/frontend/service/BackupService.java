package br.com.copa2026.frontend.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BackupService {

    public void realizarBackup() {

        try {

            String arquivo =
                    "C:\\Users\\T-GAMER\\Desktop\\Dev\\Copa2026\\database\\backup-copa2026.sql";

            ProcessBuilder builder =
                    new ProcessBuilder(

                            "C:\\Program Files\\PostgreSQL\\18\\bin\\pg_dump.exe",

                            "-U",
                            "postgres",

                            "-d",
                            "bd_copa2026",

                            "-f",
                            arquivo
                    );

            builder.environment()
                    .put(
                            "PGPASSWORD",
                            "postgres"
                    );

            builder.redirectErrorStream(true);

            Process process =
                    builder.start();

            BufferedReader reader =
                    new BufferedReader(

                            new InputStreamReader(
                                    process.getInputStream()
                            )
                    );

            String linha;

            while ((linha = reader.readLine()) != null) {

                System.out.println(linha);
            }

            int exitCode =
                    process.waitFor();

            System.out.println(
                    "BACKUP EXIT CODE: "
                            + exitCode
            );

            if (exitCode == 0) {

                System.out.println(
                        "Backup realizado com sucesso."
                );

                System.out.println(
                        "Arquivo criado em: "
                                + arquivo
                );

            } else {

                System.out.println(
                        "Erro ao realizar backup."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}