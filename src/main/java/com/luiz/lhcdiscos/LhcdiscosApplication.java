package com.luiz.lhcdiscos;

import com.luiz.lhcdiscos.models.entities.*;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.CamisetaSize;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.models.enums.Role;
import com.luiz.lhcdiscos.services.BandaService;
import com.luiz.lhcdiscos.services.PedidoService;
import com.luiz.lhcdiscos.services.ProdutoService;
import com.luiz.lhcdiscos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class LhcdiscosApplication implements WebMvcConfigurer, CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LhcdiscosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
