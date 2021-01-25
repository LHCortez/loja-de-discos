package com.luiz.lhcdiscos;

import com.luiz.lhcdiscos.models.*;
import com.luiz.lhcdiscos.models.enums.TShirtSize;
import com.luiz.lhcdiscos.repositories.BandaRepository;
import com.luiz.lhcdiscos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class LhcdiscosApplication implements WebMvcConfigurer, CommandLineRunner {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private BandaRepository bandaRepository;

    public static void main(String[] args) {
        SpringApplication.run(LhcdiscosApplication.class, args);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Disco> discos =  DiscoBuilder.newDisco().mais(5).buildAll();
//        discoRepository.saveAll(discos);

        Banda banda1 = new Banda("Theatre of Tragedy");
        Album album1 = new Vinil();
        album1.setBanda(banda1);
        banda1.getDiscos().add(album1);
        album1.setNome("Aégis");
        album1.setDescricao("Aégis é o terceiro álbum de estúdio da banda norueguesa Theatre of Tragedy, " +
                "lançado em 1998.");
        album1.setPreco(BigDecimal.TEN);
        album1.setCapa("./img/capa-velvetdarknesstheyfear.jpg");
        album1.setLancamento(LocalDate.of(1998, 5, 11));

        Banda banda2 = new Banda("Sorcier des glaces");
        Album album2 = new CD();
        album2.setBanda(banda2);
        album2.setNome("Un Monde de Glace et de Sang");
        album2.setDescricao("“Un Monde de Glace et de Sang” is the 8th studio album by the band. " +
                "They masters their cold primitive metal sound with freezing melodies, haunting atmosphere, " +
                "that paints black and white landscapes from the ancient times");
        album2.setPreco(BigDecimal.TEN);
        album2.setCapa("./img/capa-unmondedeglaceetdesang.jpg");
        album2.setLancamento(LocalDate.of(2020, 10, 30));

        Banda banda3 = new Banda("Moonspell");
        Album album3 = new CD();
        album3.setBanda(banda3);
        album3.setNome("1755");
        album3.setDescricao("1755 is the eleventh full-length album by Portuguese gothic metal band Moonspell, " +
                "released on November 3, 2017, and unlike previous albums, it is entirely sung in Portuguese. " +
                "This is a concept album, detailing the story of the 1755 Lisbon earthquake.");
        album3.setPreco(BigDecimal.TEN);
        album3.setCapa("./img/capa-1755.jpg");
        album3.setLancamento(LocalDate.of(2017, 11, 3));

        Banda banda4 = new Banda("Mercyful Fate");
        Album album4 = new K7();
        album4.setBanda(banda4);
        album4.setNome("Don't Break the Oath");
        album4.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album4.setPreco(BigDecimal.TEN);
        album4.setCapa("./img/capa-dontbreaktheoath.jpg");
        album4.setLancamento(LocalDate.of(1984, 9, 9));

//        TODO: Mudar a partir daqui ----------------------

        Banda banda5 = new Banda("Mercyful Fate");
        Album album5 = new Vinil();
        album5.setBanda(banda5);
        album5.setNome("Don't Break the Oath");
        album5.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album5.setPreco(BigDecimal.TEN);
        album5.setCapa("./img/capa-dontbreaktheoath.jpg");
        album5.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda6 = new Banda("Mercyful Fate");
        Album album6 = new Vinil();
        album6.setBanda(banda6);
        album6.setNome("Don't Break the Oath");
        album6.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album6.setPreco(BigDecimal.TEN);
        album6.setCapa("./img/capa-dontbreaktheoath.jpg");
        album6.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda7 = new Banda("Mercyful Fate");
        Album album7 = new Vinil();
        album7.setBanda(banda7);
        album7.setNome("Don't Break the Oath");
        album7.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album7.setPreco(BigDecimal.TEN);
        album7.setCapa("./img/capa-dontbreaktheoath.jpg");
        album7.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda8 = new Banda("Mercyful Fate");
        Album album8 = new Vinil();
        album8.setBanda(banda8);
        album8.setNome("Don't Break the Oath");
        album8.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album8.setPreco(BigDecimal.TEN);
        album8.setCapa("./img/capa-dontbreaktheoath.jpg");
        album8.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda9 = new Banda("Mercyful Fate");
        Album album9 = new Vinil();
        album9.setBanda(banda9);
        album9.setNome("Don't Break the Oath");
        album9.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album9.setPreco(BigDecimal.TEN);
        album9.setCapa("./img/capa-dontbreaktheoath.jpg");
        album9.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda10 = new Banda("Mercyful Fate");
        Album album10 = new Vinil();
        album10.setBanda(banda10);
        album10.setNome("Don't Break the Oath");
        album10.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album10.setPreco(BigDecimal.TEN);
        album10.setCapa("./img/capa-dontbreaktheoath.jpg");
        album10.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda11 = new Banda("Mercyful Fate");
        Album album11 = new Vinil();
        album11.setBanda(banda11);
        album11.setNome("Don't Break the Oath");
        album11.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        album11.setPreco(BigDecimal.TEN);
        album11.setCapa("./img/capa-dontbreaktheoath.jpg");
        album11.setLancamento(LocalDate.of(1984, 9, 9));

        bandaRepository.saveAll(Arrays.asList(banda1, banda2, banda3, banda4, banda5, banda6, banda7, banda8,
                banda9, banda10, banda11));
        produtoRepository.saveAll(Arrays.asList(album1, album2, album3, album4, album5, album6, album7, album8,
                album9, album10, album11));


        Banda banda12 = new Banda("Behemoth");
        Camiseta tshirt1 = new Camiseta();
        tshirt1.setBanda(banda12);
        banda12.gettShirts().add(tshirt1);
        tshirt1.setSize(TShirtSize.M);
        tshirt1.setNome("Evangelicis");
        tshirt1.setDescricao("An all-over print 'Evangelicis' Behemoth t-shirt. The print is made with a " +
                "sublimation technique, is impalpable and in full range of colours. It also won't wash out. " +
                "The material is of a highest quality 100% polyester fabrics.");
        tshirt1.setPreco(BigDecimal.TEN);
        tshirt1.setCapa("./img/capa-evangelicis.jpg");
       bandaRepository.save(banda12);
       produtoRepository.save(tshirt1);
    }
}
