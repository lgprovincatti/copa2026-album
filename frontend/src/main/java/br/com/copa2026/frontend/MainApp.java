package br.com.copa2026.frontend;

import br.com.copa2026.frontend.dto.AlbumDTO;
import br.com.copa2026.frontend.dto.DashboardDTO;
import br.com.copa2026.frontend.dto.FaltantesDTO;
import br.com.copa2026.frontend.dto.FigurinhaDTO;
import br.com.copa2026.frontend.dto.SelecaoDTO;
import br.com.copa2026.frontend.dto.SelecaoDashboardDTO;

import br.com.copa2026.frontend.service.BackupService;
import br.com.copa2026.frontend.service.DashboardService;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;

import java.util.List;
import java.util.Optional;

public class MainApp extends Application {

    private final DashboardService service =
            new DashboardService();

    private final BackupService backupService =
            new BackupService();

    private FlowPane gridFigurinhas;

    private Label lblNomeSelecao;

    private Label lblTotalSelecao;

    private Label lblPossuiSelecao;

    private Label lblFaltamSelecao;

    private Label lblPercentualSelecao;

    private Label lblCardPossui;

    private Label lblCardFaltam;

    private Label lblCardPercentual;

    private ComboBox<AlbumDTO> comboAlbuns;

    private Long albumIdSelecionado;

    @Override
    public void start(Stage stage) {

        List<AlbumDTO> albuns =
                service.carregarAlbuns();

        albumIdSelecionado =
                albuns.get(0).getId();

        DashboardDTO dto =
                service.carregarDashboard(
                        albumIdSelecionado
                );

        List<SelecaoDTO> selecoes =
                service.carregarSelecoes();

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: #F5F5F5;"
        );

        VBox painelEsquerdo =
                new VBox();

        painelEsquerdo.setPadding(
                new Insets(10)
        );

        painelEsquerdo.setSpacing(10);

        painelEsquerdo.setPrefWidth(220);

        painelEsquerdo.setStyle(
                "-fx-background-color: white;"
        );

        Label lblSelecoes =
                new Label("SELEÇÕES");

        lblSelecoes.setStyle(
                "-fx-font-size: 18px;"
                        + "-fx-font-weight: bold;"
        );

        painelEsquerdo.getChildren()
                .add(lblSelecoes);

        VBox centro =
                new VBox();

        centro.setPadding(
                new Insets(20)
        );

        centro.setSpacing(20);

        Label titulo =
                new Label(
                        "⚽ Copa do Mundo 2026"
                );

        titulo.setStyle(
                "-fx-font-size: 28px;"
                        + "-fx-font-weight: bold;"
        );

        comboAlbuns =
                new ComboBox<>();

        comboAlbuns.getItems()
                .addAll(albuns);

        if (!albuns.isEmpty()) {

            comboAlbuns.getSelectionModel()
                    .selectFirst();
        }

        comboAlbuns.setOnAction(event -> {

            AlbumDTO album =
                    comboAlbuns.getValue();

            if (album != null) {

                albumIdSelecionado =
                        album.getId();

                atualizarDashboard();
            }
        });

        HBox cards =
                new HBox();

        cards.setSpacing(20);

        HBox acoesAlbum =
                new HBox();

        acoesAlbum.setSpacing(10);

        Button btnNovoAlbum =
                new Button(
                        "NOVO ÁLBUM"
                );

        btnNovoAlbum.setStyle(
                "-fx-background-color: #2E7D32;"
                        + "-fx-text-fill: white;"
                        + "-fx-font-weight: bold;"
        );

        Button btnExcluirAlbum =
                new Button(
                        "EXCLUIR ÁLBUM"
                );

        btnExcluirAlbum.setStyle(
                "-fx-background-color: #C62828;"
                        + "-fx-text-fill: white;"
                        + "-fx-font-weight: bold;"
        );

        acoesAlbum.getChildren().addAll(
                btnNovoAlbum,
                btnExcluirAlbum
        );

        btnNovoAlbum.setOnAction(event -> {

            TextInputDialog dialog =
                    new TextInputDialog();

            dialog.setTitle(
                    "Novo Álbum"
            );

            dialog.setHeaderText(
                    "Criar novo álbum"
            );

            dialog.setContentText(
                    "Nome:"
            );

            Optional<String> resultado =
                    dialog.showAndWait();

            resultado.ifPresent(nome -> {

                service.criarAlbum(nome);

                atualizarAlbuns();
            });
        });

        btnExcluirAlbum.setOnAction(event -> {

            AlbumDTO album =
                    comboAlbuns.getValue();

            if (album == null) {
                return;
            }

            if (comboAlbuns.getItems().size() <= 1) {

                Alert alerta =
                        new Alert(
                                Alert.AlertType.WARNING
                        );

                alerta.setTitle(
                        "Operação não permitida"
                );

                alerta.setHeaderText(
                        "Último álbum"
                );

                alerta.setContentText(
                        "Não é possível excluir o último álbum."
                );

                alerta.showAndWait();

                return;
            }

            Alert alert =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            alert.setTitle(
                    "Excluir álbum"
            );

            alert.setHeaderText(
                    "Confirma exclusão?"
            );

            alert.setContentText(
                    album.getNome()
            );

            Optional<ButtonType> resultado =
                    alert.showAndWait();

            if (resultado.isPresent()
                    && resultado.get() == ButtonType.OK) {

                service.excluirAlbum(
                        album.getId()
                );

                atualizarAlbuns();
            }
        });

        Button btnExportar =
                new Button(
                        "EXPORTAR FALTANTES"
                );

        btnExportar.setStyle(
                "-fx-background-color: #6A1B9A;"
                        + "-fx-text-fill: white;"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: 14px;"
        );

        btnExportar.setOnAction(event -> {

            exportarFaltantes();
        });

        Button btnBackup =
                new Button(
                        "BACKUP"
                );

        btnBackup.setStyle(
                "-fx-background-color: #455A64;"
                        + "-fx-text-fill: white;"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: 14px;"
        );

        btnBackup.setOnAction(event -> {

            backupService.realizarBackup();

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            alert.setTitle(
                    "Backup"
            );

            alert.setHeaderText(
                    "Backup realizado"
            );

            alert.setContentText(
                    "Arquivo backup-copa2026.sql criado."
            );

            alert.showAndWait();
        });

        VBox cardTotal =
                criarCard(
                        "TOTAL",
                        dto.getTotal().toString(),
                        "#1565C0"
                );

        VBox cardPossui =
                criarCard(
                        "POSSUI",
                        dto.getPossui().toString(),
                        "#2E7D32"
                );

        VBox cardFaltam =
                criarCard(
                        "FALTAM",
                        dto.getFaltam().toString(),
                        "#C62828"
                );

        VBox cardPercentual =
                criarCard(
                        "COMPLETO",
                        String.format(
                                "%.2f%%",
                                dto.getPercentual()
                        ),
                        "#6A1B9A"
                );

        lblCardPossui =
                (Label) cardPossui.getChildren()
                        .get(1);

        lblCardFaltam =
                (Label) cardFaltam.getChildren()
                        .get(1);

        lblCardPercentual =
                (Label) cardPercentual.getChildren()
                        .get(1);

        cards.getChildren().addAll(
                cardTotal,
                cardPossui,
                cardFaltam,
                cardPercentual
        );

        VBox painelSelecao =
                new VBox();

        painelSelecao.setSpacing(10);

        painelSelecao.setPadding(
                new Insets(20)
        );

        painelSelecao.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 10;"
        );

        lblNomeSelecao =
                new Label(
                        "Selecione uma seleção"
                );

        lblNomeSelecao.setStyle(
                "-fx-font-size: 24px;"
                        + "-fx-font-weight: bold;"
        );

        lblTotalSelecao =
                new Label();

        lblPossuiSelecao =
                new Label();

        lblFaltamSelecao =
                new Label();

        lblPercentualSelecao =
                new Label();

        painelSelecao.getChildren().addAll(
                lblNomeSelecao,
                lblTotalSelecao,
                lblPossuiSelecao,
                lblFaltamSelecao,
                lblPercentualSelecao
        );

        gridFigurinhas =
                new FlowPane();

        gridFigurinhas.setHgap(10);

        gridFigurinhas.setVgap(10);

        centro.getChildren().addAll(
                titulo,
                comboAlbuns,
                acoesAlbum,
                cards,
                btnExportar,
                btnBackup,
                painelSelecao,
                gridFigurinhas
        );

        for (SelecaoDTO selecao : selecoes) {

            Button botao =
                    new Button(
                            selecao.getSigla()
                                    + " - "
                                    + selecao.getNome()
                    );

            botao.setPrefWidth(200);

            botao.setStyle(
                    "-fx-background-color: #1565C0;"
                            + "-fx-text-fill: white;"
                            + "-fx-font-weight: bold;"
            );

            botao.setOnAction(event -> {

                carregarSelecao(
                        selecao.getSigla()
                );
            });

            painelEsquerdo.getChildren()
                    .add(botao);
        }

        ScrollPane scroll =
                new ScrollPane(
                        painelEsquerdo
                );

        scroll.setFitToWidth(true);

        root.setLeft(scroll);

        root.setCenter(centro);

        Scene scene =
                new Scene(
                        root,
                        1600,
                        900
                );

        stage.setTitle(
                "Copa do Mundo 2026"
        );

        stage.setScene(scene);

        stage.show();
    }

    private void carregarSelecao(
            String sigla
    ) {

        SelecaoDashboardDTO dto =
                service.carregarSelecao(
                        albumIdSelecionado,
                        sigla
                );

        atualizarPainelSelecao(dto);

        gridFigurinhas.getChildren().clear();

        for (FigurinhaDTO figurinha
                : dto.getFigurinhas()) {

            Button btn =
                    new Button(
                            figurinha.getCodigo()
                    );

            btn.setPrefWidth(120);

            btn.setPrefHeight(60);

            atualizarCorBotao(
                    btn,
                    figurinha.getPossui()
            );

            btn.setOnAction(event -> {

                service.alternarFigurinha(
                        albumIdSelecionado,
                        figurinha.getId()
                );

                if ("S".equals(
                        figurinha.getPossui()
                )) {

                    figurinha.setPossui("N");

                } else {

                    figurinha.setPossui("S");
                }

                atualizarCorBotao(
                        btn,
                        figurinha.getPossui()
                );

                carregarSelecao(sigla);

                DashboardDTO dashboard =
                        service.carregarDashboard(
                                albumIdSelecionado
                        );

                lblCardPossui.setText(
                        dashboard.getPossui().toString()
                );

                lblCardFaltam.setText(
                        dashboard.getFaltam().toString()
                );

                lblCardPercentual.setText(
                        String.format(
                                "%.2f%%",
                                dashboard.getPercentual()
                        )
                );
            });

            gridFigurinhas.getChildren()
                    .add(btn);
        }
    }

    private void atualizarPainelSelecao(
            SelecaoDashboardDTO dto
    ) {

        lblNomeSelecao.setText(
                dto.getNome()
        );

        lblTotalSelecao.setText(
                "Total: "
                        + dto.getTotal()
        );

        lblPossuiSelecao.setText(
                "Possui: "
                        + dto.getPossui()
        );

        lblFaltamSelecao.setText(
                "Faltam: "
                        + dto.getFaltam()
        );

        lblPercentualSelecao.setText(
                "Percentual: "
                        + String.format(
                        "%.2f%%",
                        dto.getPercentual()
                )
        );
    }

    private void atualizarDashboard() {

        DashboardDTO dto =
                service.carregarDashboard(
                        albumIdSelecionado
                );

        lblCardPossui.setText(
                dto.getPossui().toString()
        );

        lblCardFaltam.setText(
                dto.getFaltam().toString()
        );

        lblCardPercentual.setText(
                String.format(
                        "%.2f%%",
                        dto.getPercentual()
                )
        );

        limparSelecao();
    }

    private void limparSelecao() {

        lblNomeSelecao.setText(
                "Selecione uma seleção"
        );

        lblTotalSelecao.setText("");

        lblPossuiSelecao.setText("");

        lblFaltamSelecao.setText("");

        lblPercentualSelecao.setText("");

        gridFigurinhas.getChildren().clear();
    }

    private void atualizarAlbuns() {

        List<AlbumDTO> albuns =
                service.carregarAlbuns();

        comboAlbuns.getItems().clear();

        comboAlbuns.getItems()
                .addAll(albuns);

        if (!albuns.isEmpty()) {

            comboAlbuns.getSelectionModel()
                    .selectLast();

            albumIdSelecionado =
                    comboAlbuns.getValue()
                            .getId();

            atualizarDashboard();
        }
    }

    private void atualizarCorBotao(
            Button btn,
            String possui
    ) {

        if ("S".equals(possui)) {

            btn.setStyle(
                    "-fx-background-color: #2E7D32;"
                            + "-fx-text-fill: white;"
                            + "-fx-font-weight: bold;"
            );

        } else {

            btn.setStyle(
                    "-fx-background-color: #C62828;"
                            + "-fx-text-fill: white;"
                            + "-fx-font-weight: bold;"
            );
        }
    }

    private VBox criarCard(
            String titulo,
            String valor,
            String cor
    ) {

        VBox card =
                new VBox();

        card.setAlignment(
                Pos.CENTER
        );

        card.setSpacing(10);

        card.setPadding(
                new Insets(20)
        );

        card.setPrefWidth(250);

        card.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 10;"
                        + "-fx-border-radius: 10;"
                        + "-fx-border-color: #E0E0E0;"
        );

        Label lblTitulo =
                new Label(titulo);

        lblTitulo.setStyle(
                "-fx-font-size: 16px;"
                        + "-fx-font-weight: bold;"
        );

        Label lblValor =
                new Label(valor);

        lblValor.setTextFill(
                Color.web(cor)
        );

        lblValor.setStyle(
                "-fx-font-size: 36px;"
                        + "-fx-font-weight: bold;"
        );

        card.getChildren().addAll(
                lblTitulo,
                lblValor
        );

        return card;
    }

    private void exportarFaltantes() {

        List<FaltantesDTO> lista =
                service.carregarFaltantes(
                        albumIdSelecionado
                );

        StringBuilder texto =
                new StringBuilder();

        for (FaltantesDTO dto : lista) {

            texto.append(
                    dto.getSigla()
            ).append("\n");

            for (String figurinha
                    : dto.getFaltantes()) {

                texto.append(figurinha)
                        .append("\n");
            }

            texto.append("\n");
        }

        Stage janela =
                new Stage();

        VBox root =
                new VBox();

        root.setPadding(
                new Insets(20)
        );

        root.setSpacing(10);

        TextArea area =
                new TextArea(
                        texto.toString()
                );

        area.setWrapText(true);

        area.setPrefSize(
                600,
                800
        );

        Button btnSalvar =
                new Button(
                        "SALVAR TXT"
                );

        btnSalvar.setOnAction(event -> {

            salvarArquivo(
                    texto.toString()
            );
        });

        root.getChildren().addAll(
                area,
                btnSalvar
        );

        Scene scene =
                new Scene(
                        root,
                        700,
                        900
                );

        janela.setTitle(
                "Faltantes"
        );

        janela.setScene(scene);

        janela.show();
    }

    private void salvarArquivo(
            String conteudo
    ) {

        try {

            FileChooser chooser =
                    new FileChooser();

            chooser.setTitle(
                    "Salvar faltantes"
            );

            chooser.getExtensionFilters()
                    .add(

                            new FileChooser.ExtensionFilter(
                                    "Arquivo TXT",
                                    "*.txt"
                            )
                    );

            chooser.setInitialFileName(
                    "faltantes.txt"
            );

            File arquivo =
                    chooser.showSaveDialog(
                            null
                    );

            if (arquivo != null) {

                FileWriter writer =
                        new FileWriter(
                                arquivo
                        );

                writer.write(conteudo);

                writer.close();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}