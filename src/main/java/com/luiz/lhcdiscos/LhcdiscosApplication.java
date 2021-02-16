package com.luiz.lhcdiscos;

import com.luiz.lhcdiscos.models.*;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.CamisetaSize;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.models.enums.Role;
import com.luiz.lhcdiscos.repositories.BandaRepository;
import com.luiz.lhcdiscos.repositories.ProdutoRepository;
import com.luiz.lhcdiscos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class LhcdiscosApplication implements WebMvcConfigurer, CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private BandaRepository bandaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(LhcdiscosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Banda banda1 = new Banda("Theatre of Tragedy");
        banda1.setGenero(Genero.GOTHICMETAL);
        Album album1 = new Album();
        album1.setBanda(banda1);
        banda1.getProdutos().add(album1);
        album1.setNome("Aégis");
        album1.setFormato(AlbumFormato.VINIL);
        album1.setDescricao("Aégis é o terceiro álbum de estúdio da banda norueguesa Theatre of Tragedy, " +
                "lançado em 1998.");
        album1.setPreco(BigDecimal.TEN);
        album1.setCapa("/img/capa-velvetdarknesstheyfear.jpg");
        album1.setLancamento(LocalDate.of(1998, 5, 11));

        Banda banda2 = new Banda("Sorcier des glaces");
        banda2.setGenero(Genero.BLACKMETAL);
        Album album2 = new Album();
        album2.setBanda(banda2);
        banda2.getProdutos().add(album2);
        album2.setNome("Un Monde de Glace et de Sang");
        album2.setFormato(AlbumFormato.VINIL);
        album2.setDescricao("“Un Monde de Glace et de Sang” is the 8th studio album by the band. " +
                "They masters their cold primitive metal sound with freezing melodies, haunting atmosphere, " +
                "that paints black and white landscapes from the ancient times");
        album2.setPreco(BigDecimal.TEN);
        album2.setCapa("/img/capa-unmondedeglaceetdesang.jpg");
        album2.setLancamento(LocalDate.of(2020, 10, 30));

        Banda banda3 = new Banda("Moonspell");
        banda3.setGenero(Genero.GOTHICMETAL);
        Album album3 = new Album();
        banda3.getProdutos().add(album3);
        album3.setBanda(banda3);
        album3.setNome("1755");
        album3.setFormato(AlbumFormato.CD);
        album3.setDescricao("1755 is the eleventh full-length album by Portuguese gothic metal band Moonspell, " +
                "released on November 3, 2017, and unlike previous albums, it is entirely sung in Portuguese. " +
                "This is a concept album, detailing the story of the 1755 Lisbon earthquake.");
        album3.setPreco(BigDecimal.TEN);
        album3.setCapa("/img/capa-1755.jpg");
        album3.setLancamento(LocalDate.of(2017, 11, 3));

        Banda banda4 = new Banda("Mercyful Fate");
        banda4.setGenero(Genero.HEAVYMETAL);
        Album album4 = new Album();
        banda4.getProdutos().add(album4);
        album4.setBanda(banda4);
        album4.setNome("Don't Break the Oath");
        album4.setFormato(AlbumFormato.VINIL);
        album4.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album4.setPreco(BigDecimal.TEN);
        album4.setCapa("/img/capa-dontbreaktheoath.jpg");
        album4.setLancamento(LocalDate.of(1984, 9, 9));

//        TODO: Mudar a partir daqui ----------------------

        Banda banda5 = new Banda("Mercyful Fate");
        banda5.setGenero(Genero.HEAVYMETAL);
        Album album5 = new Album();
        banda5.getProdutos().add(album5);
        album5.setBanda(banda5);
        album5.setNome("Don't Break the Oath");
        album5.setFormato(AlbumFormato.DVD);
        album5.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album5.setPreco(BigDecimal.TEN);
        album5.setCapa("/img/capa-dontbreaktheoath.jpg");
        album5.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda6 = new Banda("Mercyful Fate");
        banda6.setGenero(Genero.HEAVYMETAL);
        Album album6 = new Album();
        banda6.getProdutos().add(album6);
        album6.setBanda(banda6);
        album6.setNome("Don't Break the Oath");
        album6.setFormato(AlbumFormato.CASSETE);
        album6.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album6.setPreco(BigDecimal.TEN);
        album6.setCapa("/img/capa-dontbreaktheoath.jpg");
        album6.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda7 = new Banda("Mercyful Fate");
        banda7.setGenero(Genero.HEAVYMETAL);
        Album album7 = new Album();
        banda7.getProdutos().add(album7);
        album7.setBanda(banda7);
        album7.setNome("Don't Break the Oath");
        album7.setFormato(AlbumFormato.VINIL);
        album7.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album7.setPreco(BigDecimal.TEN);
        album7.setCapa("/img/capa-dontbreaktheoath.jpg");
        album7.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda8 = new Banda("Mercyful Fate");
        banda8.setGenero(Genero.HEAVYMETAL);
        Album album8 = new Album();
        banda8.getProdutos().add(album8);
        album8.setBanda(banda8);
        album8.setNome("Don't Break the Oath");
        album8.setFormato(AlbumFormato.VINIL);
        album8.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album8.setPreco(BigDecimal.TEN);
        album8.setCapa("/img/capa-dontbreaktheoath.jpg");
        album8.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda9 = new Banda("Mercyful Fate");
        banda9.setGenero(Genero.HEAVYMETAL);
        Album album9 = new Album();
        banda9.getProdutos().add(album9);
        album9.setBanda(banda9);
        album9.setNome("Don't Break the Oath");
        album9.setFormato(AlbumFormato.VINIL);
        album9.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album9.setPreco(BigDecimal.TEN);
        album9.setCapa("/img/capa-dontbreaktheoath.jpg");
        album9.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda10 = new Banda("Mercyful Fate");
        banda10.setGenero(Genero.HEAVYMETAL);
        Album album10 = new Album();
        banda10.getProdutos().add(album10);
        album10.setBanda(banda10);
        album10.setNome("Don't Break the Oath");
        album10.setFormato(AlbumFormato.VINIL);
        album10.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album10.setPreco(BigDecimal.TEN);
        album10.setCapa("/img/capa-dontbreaktheoath.jpg");
        album10.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda11 = new Banda("Mercyful Fate");
        Album album11 = new Album();
        banda10.getProdutos().add(album11);
        album11.setBanda(banda10);
        album11.setNome("asdfasdfsdf");
        album11.setFormato(AlbumFormato.VINIL);
        album11.setDescricao("sdfasfsdfsdfa.");
        album11.setPreco(BigDecimal.TEN);
        album11.setCapa("/img/capa-dontbreaktheoath.jpg");
        album11.setLancamento(LocalDate.of(1984, 9, 9));

        bandaRepository.saveAll(Arrays.asList(banda1, banda2, banda3, banda4, banda5, banda6, banda7, banda8,
                banda9, banda10, banda11));
        produtoRepository.saveAll(Arrays.asList(album1, album2, album3, album4, album5, album6, album7, album8,
                album9, album10, album11));


        Banda banda12 = new Banda("Behemoth");
        banda12.setGenero(Genero.DEATHMETAL);
        Camiseta tshirt1 = new Camiseta();
        tshirt1.setBanda(banda12);
        banda12.getProdutos().add(tshirt1);
        tshirt1.setSize(CamisetaSize.M);
        tshirt1.setNome("Evangelicis");
        tshirt1.setDescricao("An all-over print 'Evangelicis' Behemoth t-shirt. The print is made with a " +
                "sublimation technique, is impalpable and in full range of colours. It also won't wash out. " +
                "The material is of a highest quality 100% polyester fabrics.");
        tshirt1.setPreco(BigDecimal.TEN);
        tshirt1.setCapa("/img/capa-evangelicis.jpg");
       bandaRepository.save(banda12);
       produtoRepository.save(tshirt1);

        Usuario usuario = new Usuario();
        usuario.setNome("Admin");
        usuario.setEmail("admin@casadocodigo.com.br");
        usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");


        usuario.addRoles(Role.ROLE_ADMIN, Role.ROLE_USER);
        usuarioRepository.save(usuario);


    }
}
