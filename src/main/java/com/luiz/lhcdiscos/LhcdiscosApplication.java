package com.luiz.lhcdiscos;

import com.luiz.lhcdiscos.builders.DiscoBuilder;
import com.luiz.lhcdiscos.models.Banda;
import com.luiz.lhcdiscos.models.Disco;
import com.luiz.lhcdiscos.repositories.BandaRepository;
import com.luiz.lhcdiscos.repositories.DiscoRepository;
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
import java.util.List;

@SpringBootApplication
public class LhcdiscosApplication implements WebMvcConfigurer, CommandLineRunner {

    @Autowired
    private DiscoRepository discoRepository;
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
        Disco disco1 = new Disco();
        disco1.setBanda(banda1);
        banda1.getDiscos().add(disco1);
        disco1.setTitulo("Aégis");
        disco1.setDescricao("Aégis é o terceiro álbum de estúdio da banda norueguesa Theatre of Tragedy, " +
                "lançado em 1998.");
        disco1.setPreco(BigDecimal.TEN);
        disco1.setCapa("./img/capa-velvetdarknesstheyfear.jpg");
        disco1.setLancamento(LocalDate.of(1998, 5, 11));

        Banda banda2 = new Banda("Sorcier des glaces");
        Disco disco2 = new Disco();
        disco2.setBanda(banda2);
        disco2.setTitulo("Un Monde de Glace et de Sang");
        disco2.setDescricao("“Un Monde de Glace et de Sang” is the 8th studio album by the band. " +
                "They masters their cold primitive metal sound with freezing melodies, haunting atmosphere, " +
                "that paints black and white landscapes from the ancient times");
        disco2.setPreco(BigDecimal.TEN);
        disco2.setCapa("./img/capa-unmondedeglaceetdesang.jpg");
        disco2.setLancamento(LocalDate.of(2020, 10, 30));

        Banda banda3 = new Banda("Moonspell");
        Disco disco3 = new Disco();
        disco3.setBanda(banda3);
        disco3.setTitulo("1755");
        disco3.setDescricao("1755 is the eleventh full-length album by Portuguese gothic metal band Moonspell, " +
                "released on November 3, 2017, and unlike previous albums, it is entirely sung in Portuguese. " +
                "This is a concept album, detailing the story of the 1755 Lisbon earthquake.");
        disco3.setPreco(BigDecimal.TEN);
        disco3.setCapa("./img/capa-1755.jpg");
        disco3.setLancamento(LocalDate.of(2017, 11, 3));

        Banda banda4 = new Banda("Mercyful Fate");
        Disco disco4 = new Disco();
        disco4.setBanda(banda4);
        disco4.setTitulo("Don't Break the Oath");
        disco4.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        disco4.setPreco(BigDecimal.TEN);
        disco4.setCapa("./img/capa-dontbreaktheoath.jpg");
        disco4.setLancamento(LocalDate.of(1984, 9, 9));

//        TODO: Mudar a partir daqui ----------------------

        Banda banda5 = new Banda("Mercyful Fate");
        Disco disco5 = new Disco();
        disco5.setBanda(banda5);
        disco5.setTitulo("Don't Break the Oath");
        disco5.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        disco5.setPreco(BigDecimal.TEN);
        disco5.setCapa("./img/capa-dontbreaktheoath.jpg");
        disco5.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda6 = new Banda("Mercyful Fate");
        Disco disco6 = new Disco();
        disco5.setBanda(banda6);
        disco6.setTitulo("Don't Break the Oath");
        disco6.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        disco6.setPreco(BigDecimal.TEN);
        disco6.setCapa("./img/capa-dontbreaktheoath.jpg");
        disco6.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda7 = new Banda("Mercyful Fate");
        Disco disco7 = new Disco();
        disco7.setBanda(banda7);
        disco7.setTitulo("Don't Break the Oath");
        disco7.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        disco7.setPreco(BigDecimal.TEN);
        disco7.setCapa("./img/capa-dontbreaktheoath.jpg");
        disco7.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda8 = new Banda("Mercyful Fate");
        Disco disco8 = new Disco();
        disco8.setBanda(banda8);
        disco8.setTitulo("Don't Break the Oath");
        disco8.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        disco8.setPreco(BigDecimal.TEN);
        disco8.setCapa("./img/capa-dontbreaktheoath.jpg");
        disco8.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda9 = new Banda("Mercyful Fate");
        Disco disco9 = new Disco();
        disco9.setBanda(banda9);
        disco9.setTitulo("Don't Break the Oath");
        disco9.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        disco9.setPreco(BigDecimal.TEN);
        disco9.setCapa("./img/capa-dontbreaktheoath.jpg");
        disco9.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda10 = new Banda("Mercyful Fate");
        Disco disco10 = new Disco();
        disco10.setBanda(banda10);
        disco10.setTitulo("Don't Break the Oath");
        disco10.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        disco10.setPreco(BigDecimal.TEN);
        disco10.setCapa("./img/capa-dontbreaktheoath.jpg");
        disco10.setLancamento(LocalDate.of(1984, 9, 9));

        Banda banda11 = new Banda("Mercyful Fate");
        Disco disco11 = new Disco();
        disco11.setBanda(banda11);
        disco11.setTitulo("Don't Break the Oath");
        disco11.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, " +
                "lançado em 1984.");
        disco11.setPreco(BigDecimal.TEN);
        disco11.setCapa("./img/capa-dontbreaktheoath.jpg");
        disco11.setLancamento(LocalDate.of(1984, 9, 9));

        bandaRepository.saveAll(Arrays.asList(banda1, banda2, banda3, banda4, banda5, banda6, banda7, banda8,
                banda9, banda10, banda11));
        discoRepository.saveAll(Arrays.asList(disco1, disco2, disco3, disco4, disco5, disco6, disco7, disco8,
                disco9, disco10, disco11));
    }
}
