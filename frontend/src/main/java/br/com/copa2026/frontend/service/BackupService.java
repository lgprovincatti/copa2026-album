package br.com.copa2026.frontend.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class BackupService {

    private static final String PG_DUMP =

            "C:\\Program Files\\PostgreSQL\\18\\bin\\pg_dump.exe";

    private static final String PSQL =

            "C:\\Program Files\\PostgreSQL\\18\\bin\\psql.exe";

    private static final String DATABASE =

            "bd_copa2026";

    private static final String USER =

            "postgres";

    private static final String PASSWORD =

            "postgres";

    public void realizarBackup() {

        try {

            String arquivo =
                    "C:\\Users\\T-GAMER\\Desktop\\Dev\\Copa2026\\database\\backup-copa2026.sql";

            ProcessBuilder builder =
                    new ProcessBuilder(

                            PG_DUMP,

                            "-U",
                            USER,

                            "-d",
                            DATABASE,

                            "-f",
                            arquivo
                    );

            builder.environment()
                    .put(
                            "PGPASSWORD",
                            PASSWORD
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

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public boolean restaurarBackup(
            File arquivo
    ) {

        try {

            ProcessBuilder builder =
                    new ProcessBuilder(

                            PSQL,

                            "-U",
                            USER,

                            "-d",
                            DATABASE,

                            "-f",
                            arquivo.getAbsolutePath()
                    );

            builder.environment()
                    .put(
                            "PGPASSWORD",
                            PASSWORD
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
                    "RESTORE EXIT CODE: "
                            + exitCode
            );

            return exitCode == 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}