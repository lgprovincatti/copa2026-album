package br.com.copa2026.frontend.service;

import br.com.copa2026.frontend.dto.AlbumDTO;
import br.com.copa2026.frontend.dto.CreateAlbumDTO;
import br.com.copa2026.frontend.dto.DashboardDTO;
import br.com.copa2026.frontend.dto.DashboardGrupoDTO;
import br.com.copa2026.frontend.dto.FaltantesDTO;
import br.com.copa2026.frontend.dto.SelecaoDTO;
import br.com.copa2026.frontend.dto.SelecaoDashboardDTO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.InputStream;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import java.util.List;

public class DashboardService {

    private final ObjectMapper mapper =
            new ObjectMapper();

    public DashboardDTO carregarDashboard(
            Long albumId
    ) {

        try {

            URI uri = URI.create(
                    "http://localhost:8080/dashboard?albumId="
                            + albumId
            );

            URL url = uri.toURL();

            HttpURLConnection conexao =
                    (HttpURLConnection)
                            url.openConnection();

            conexao.setRequestMethod("GET");

            InputStream input =
                    conexao.getInputStream();

            return mapper.readValue(
                    input,
                    DashboardDTO.class
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    public List<DashboardGrupoDTO> carregarGrupos(
            Long albumId
    ) {

        try {

            URI uri = URI.create(
                    "http://localhost:8080/dashboard/grupos?albumId="
                            + albumId
            );

            URL url = uri.toURL();

            HttpURLConnection conexao =
                    (HttpURLConnection)
                            url.openConnection();

            conexao.setRequestMethod("GET");

            InputStream input =
                    conexao.getInputStream();

            return mapper.readValue(
                    input,
                    new TypeReference<>() {
                    }
            );

        } catch (Exception e) {

            e.printStackTrace();

            return List.of();
        }
    }

    public List<SelecaoDTO> carregarSelecoes() {

        try {

            URI uri = URI.create(
                    "http://localhost:8080/selecoes"
            );

            URL url = uri.toURL();

            HttpURLConnection conexao =
                    (HttpURLConnection)
                            url.openConnection();

            conexao.setRequestMethod("GET");

            InputStream input =
                    conexao.getInputStream();

            return mapper.readValue(
                    input,
                    new TypeReference<>() {
                    }
            );

        } catch (Exception e) {

            e.printStackTrace();

            return List.of();
        }
    }

    public SelecaoDashboardDTO carregarSelecao(
            Long albumId,
            String sigla
    ) {

        try {

            URI uri = URI.create(
                    "http://localhost:8080/selecao/"
                            + sigla
                            + "?albumId="
                            + albumId
            );

            URL url = uri.toURL();

            HttpURLConnection conexao =
                    (HttpURLConnection)
                            url.openConnection();

            conexao.setRequestMethod("GET");

            InputStream input =
                    conexao.getInputStream();

            return mapper.readValue(
                    input,
                    SelecaoDashboardDTO.class
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    public void alternarFigurinha(
            Long albumId,
            Long figurinhaId
    ) {

        try {

            URI uri = URI.create(
                    "http://localhost:8080/album/"
                            + figurinhaId
                            + "?albumId="
                            + albumId
            );

            URL url = uri.toURL();

            HttpURLConnection conexao =
                    (HttpURLConnection)
                            url.openConnection();

            conexao.setRequestMethod("PUT");

            conexao.getResponseCode();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<FaltantesDTO> carregarFaltantes(
            Long albumId
    ) {

        try {

            URI uri = URI.create(
                    "http://localhost:8080/faltantes?albumId="
                            + albumId
            );

            URL url = uri.toURL();

            HttpURLConnection conexao =
                    (HttpURLConnection)
                            url.openConnection();

            conexao.setRequestMethod("GET");

            InputStream input =
                    conexao.getInputStream();

            return mapper.readValue(
                    input,
                    new TypeReference<>() {
                    }
            );

        } catch (Exception e) {

            e.printStackTrace();

            return List.of();
        }
    }

    public List<AlbumDTO> carregarAlbuns() {

        try {

            URI uri = URI.create(
                    "http://localhost:8080/albuns"
            );

            URL url = uri.toURL();

            HttpURLConnection conexao =
                    (HttpURLConnection)
                            url.openConnection();

            conexao.setRequestMethod("GET");

            InputStream input =
                    conexao.getInputStream();

            return mapper.readValue(
                    input,
                    new TypeReference<>() {
                    }
            );

        } catch (Exception e) {

            e.printStackTrace();

            return List.of();
        }
    }

    public void criarAlbum(
            String nome
    ) {

        try {

            URI uri = URI.create(
                    "http://localhost:8080/albuns"
            );

            URL url = uri.toURL();

            HttpURLConnection conexao =
                    (HttpURLConnection)
                            url.openConnection();

            conexao.setRequestMethod("POST");

            conexao.setDoOutput(true);

            conexao.setRequestProperty(
                    "Content-Type",
                    "application/json"
            );

            CreateAlbumDTO dto =
                    new CreateAlbumDTO();

            dto.setNome(nome);

            ObjectWriter writer =
                    mapper.writer();

            String json =
                    writer.writeValueAsString(dto);

            OutputStream os =
                    conexao.getOutputStream();

            os.write(json.getBytes());

            os.flush();

            os.close();

            conexao.getResponseCode();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void excluirAlbum(
            Long albumId
    ) {

        try {

            URI uri = URI.create(
                    "http://localhost:8080/albuns/"
                            + albumId
            );

            URL url = uri.toURL();

            HttpURLConnection conexao =
                    (HttpURLConnection)
                            url.openConnection();

            conexao.setRequestMethod("DELETE");

            conexao.getResponseCode();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}