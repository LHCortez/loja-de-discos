package com.luiz.lhcdiscos;

import com.luiz.lhcdiscos.models.*;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.CamisetaSize;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.models.enums.Role;
import com.luiz.lhcdiscos.repositories.BandaRepository;
import com.luiz.lhcdiscos.repositories.ProdutoRepository;
import com.luiz.lhcdiscos.repositories.UsuarioRepository;
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

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private BandaService bandaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PedidoService pedidoService;

    public static void main(String[] args) {
        SpringApplication.run(LhcdiscosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        BANDAS ----------------------------------------------------------------------------------------------------

        Banda mercyfulFate = new Banda("Mercyful Fate", Genero.HEAVYMETAL);
        Banda judasPriest = new Banda("Judas Priest", Genero.HEAVYMETAL);

        Banda behemoth = new Banda("Behemoth", Genero.DEATHMETAL);
        Banda death = new Banda("Death", Genero.DEATHMETAL);

        Banda moonspell = new Banda("Moonspell", Genero.GOTHICMETAL);
        Banda paradiseLost = new Banda("Paradise Lost", Genero.GOTHICMETAL);
        Banda theatreOfTragedy = new Banda("Theatre of Tragedy", Genero.GOTHICMETAL);

        Banda megadeth = new Banda("Megadeth", Genero.THRASHMETAL);
        Banda sepultura = new Banda("Sepultura", Genero.THRASHMETAL);

        Banda darkFortress = new Banda("Dark Fortress", Genero.BLACKMETAL);
        Banda mgla = new Banda("Mgla", Genero.BLACKMETAL);
        Banda darkthrone = new Banda("Darkthrone", Genero.BLACKMETAL);
        Banda emperor = new Banda("Emperor", Genero.BLACKMETAL);
        Banda celticFrost = new Banda("Celtic Frost", Genero.BLACKMETAL);

        Banda candlemass = new Banda("Candlemass", Genero.DOOMMETAL);
        Banda saintvitus = new Banda("Saint Vitus", Genero.DOOMMETAL);

        Banda carnifex = new Banda("Carnifex", Genero.DEATHCORE);
        Banda thyArtIsMurder = new Banda("Thy Art Is Murder", Genero.DEATHCORE);

        Banda petShopBoys = new Banda("Pet Shop Boys", Genero.OUTROS);
        Banda babymetal = new Banda("Baby Metal", Genero.OUTROS);

        bandaService.saveAll(Arrays.asList(mercyfulFate, judasPriest, behemoth, death, emperor, moonspell, paradiseLost,
                theatreOfTragedy, megadeth, sepultura, darkFortress, mgla, darkthrone, candlemass, saintvitus,
                carnifex, thyArtIsMurder, petShopBoys, babymetal, celticFrost));

//        PRODUTOS ---------------------------------------------------------------------------------------------------

        Album melissa = new Album();
        melissa.setNome("Melissa");
        melissa.setFormato(AlbumFormato.VINIL);
        melissa.setCapa("https://upload.wikimedia.org/wikipedia/pt/d/d6/Melissa.jpg");
        melissa.setLancamento(LocalDate.of(1989, 10, 30));
        melissa.setPreco(BigDecimal.valueOf(110));
        melissa.setDescricao("Melissa é o primeiro álbum da banda de heavy metal Mercyful Fate, lançado em 1983. Foi eleito o 2º maior álbum de metal extremo de todos os tempos pelo site Metal-Rules.com.");
        melissa.setBanda(mercyfulFate);

        Album dontBreakTheOath = new Album();
        dontBreakTheOath.setNome("Don't Break The Oath");
        dontBreakTheOath.setFormato(AlbumFormato.VINIL);
        dontBreakTheOath.setCapa("https://upload.wikimedia.org/wikipedia/pt/thumb/5/5b/Don%27t_Break_the_Oath.jpg/220px-Don%27t_Break_the_Oath.jpg");
        dontBreakTheOath.setLancamento(LocalDate.of(1984, 9, 7));
        dontBreakTheOath.setPreco(BigDecimal.valueOf(120));
        dontBreakTheOath.setDescricao("Don't Break the Oath é o segundo álbum da banda dinamarquesa de heavy metal Mercyful Fate, lançado em 1984.\n" +
                "\n" +
                "O álbum foi remasterizado e relançado em 1997 pela Roadrunner Records. O site Metal-Rules.com colocou Don't Break the Oath como o maior álbum de metal extremo de todos os tempos.");
        dontBreakTheOath.setBanda(mercyfulFate);

        Album sadWingsOfDestiny = new Album();
        sadWingsOfDestiny.setNome("Sad Wings of Destiny");
        sadWingsOfDestiny.setFormato(AlbumFormato.VINIL);
        sadWingsOfDestiny.setCapa("https://upload.wikimedia.org/wikipedia/pt/thumb/5/5f/Sad_Wings_of_Destiny.jpg/220px-Sad_Wings_of_Destiny.jpg");
        sadWingsOfDestiny.setLancamento(LocalDate.of(1976, 11, 23));
        sadWingsOfDestiny.setPreco(BigDecimal.valueOf(200));
        sadWingsOfDestiny.setDescricao("Sad Wings of Destiny é o segundo álbum de estúdio da banda Judas Priest, lançado a 23 de Março de 1976.\n" +
                "\n" +
                "O desenho da capa do álbum, intitulado \"Fallen Angels, \" foi elaborado por Patrick Woodroffe. É uma das imagens de marca da banda, conhecida como \"Judas Priest Cross\".\n" +
                "\n" +
                "O disco possui mais participação do vocalista Rob Halford, uma vez que no primeiro disco, a banda ainda aproveitava dispersar composições do antigo vocalista, Al Atkins, que não chegou a gravar nada oficial com a banda.\n" +
                "\n" +
                "Sad Wings of Destiny foi o último álbum editado pela Gull Records. Foi devido à falta de apoio por parte desta empresa, que levou a banda a assinar contrato com a Columbia Records. Logo de seguida a banda perdeu todos os direitos em relação aos dois primeiros discos, bem como todas as demos até aí gravadas.");
        sadWingsOfDestiny.setBanda(judasPriest);

        Album painkiller = new Album();
        painkiller.setNome("Painkiller");
        painkiller.setFormato(AlbumFormato.VINIL);
        painkiller.setCapa("https://upload.wikimedia.org/wikipedia/pt/thumb/6/66/Painkiller.jpg/220px-Painkiller.jpg");
        painkiller.setLancamento(LocalDate.of(1990, 9, 3));
        painkiller.setPreco(BigDecimal.valueOf(130));
        painkiller.setDescricao("Painkiller é o décimo segundo álbum de estúdio da banda Judas Priest, lançado a 3 de Setembro de 1990.\n \n O álbum foi gravado em Brignoles, na França, nos estúdios Miraval e mixado no Wisseloord Studios, Hilversum, na Holanda no início de 1990. O álbum foi indicado ao Grammy de Melhor Performance de Metal, em 20 de fevereiro de 1991, no 33º Annual Grammy Awards. O álbum já vendeu mais de 2 milhões de cópias pelo mundo.");
        painkiller.setBanda(judasPriest);

        Album firepower = new Album();
        firepower.setNome("Firepower");
        firepower.setFormato(AlbumFormato.CD);
        firepower.setCapa("https://upload.wikimedia.org/wikipedia/pt/thumb/6/62/Firepower.jpg/220px-Firepower.jpg");
        firepower.setLancamento(LocalDate.of(2018, 3, 9));
        firepower.setPreco(BigDecimal.valueOf(50));
        firepower.setDescricao("Firepower é o décimo-oitavo álbum de estúdio da banda de heavy metal britânica Judas Priest, lançado em 2018. É o último trabalho da banda com o guitarrista Glenn Tipton, que se afastou das atividades devido ao mal de parkinson, realizando aparições esporádicas na turnê de divulgação do disco.\n" +
                "\n" +
                "O álbum é o sucessor de Redeemer of Souls, de 2014 e tem sido aclamado como o melhor trabalho do Judas Priest desde Jugulator, de 1997. Segundo o guitarrista Richie Faulkner, embora a banda tenha se empenhado em fazer um som moderno, os músicos também buscaram referências na própria discografia do Judas Priest, algo que é largamente citado nas críticas positivas.");
        firepower.setBanda(judasPriest);

        Camiseta evangelicis = new Camiseta();
        evangelicis.setNome("Evangelicis");
        evangelicis.setSize(CamisetaSize.M);
        evangelicis.setCapa("https://behemoth-store.com/eng_pm_Evangelicis-Behemoth-all-over-print-t-shirt-785_1.jpg");
        evangelicis.setLancamento(LocalDate.of(2018, 10, 30));
        evangelicis.setPreco(BigDecimal.valueOf(60));
        evangelicis.setDescricao("An all-over print 'Evangelicis' Behemoth t-shirt. \n" +
                "\n" +
                "The print is made with a sublimation technique, is impalpable and in full range of colours. It also won't wash out. The material is of a highest quality 100% polyester fabrics , same one which is used for clothing for sports activities. It has a very nice feeling, absorbs moisture and is fully breathable. ");
        evangelicis.setBanda(behemoth);

        Camiseta demigod = new Camiseta();
        demigod.setNome("Demigod");
        demigod.setSize(CamisetaSize.G);
        demigod.setCapa("https://behemoth-store.com/eng_pm_Behemoth-Demigod-t-shirt-782_2.jpg");
        demigod.setLancamento(LocalDate.of(2018, 10, 30));
        demigod.setPreco(BigDecimal.valueOf(60));
        demigod.setDescricao("A black 'Demigod' Behemoth t-shirt. Print on the front and back.\n" +
                "\n" +
                "The t-shirt is of Premium Quality, it has been made in Poland especially for Behemoth and its quality and durability is far much better than most of the other t-shirts present on the market. ");
        demigod.setBanda(behemoth);

        Patch leprosy = new Patch();
        leprosy.setNome("Leprosy");
        leprosy.setCapa("https://s3-sa-east-1.amazonaws.com/loja2/9fcdc0d54a70bfd4a9e8031120938ada.jpg");
        leprosy.setLancamento(LocalDate.of(2019, 10, 30));
        leprosy.setPreco(BigDecimal.valueOf(30));
        leprosy.setDescricao("Produto oficial, licenciado pela banda e importado da Inglaterra.\n" +
                "Tipo: Microbordado (woven)\n" +
                "Tamanho (aprox.): 9,7 x 9,8cm");
        leprosy.setBanda(death);

        Album inTheNighsideEclipse = new Album();
        inTheNighsideEclipse.setNome("In the Nightside Eclipse");
        inTheNighsideEclipse.setFormato(AlbumFormato.CASSETE);
        inTheNighsideEclipse.setCapa("https://img.discogs.com/xootpQ7AvfIDv3sED5WLY1r55GA=/fit-in/410x308/filters:strip_icc():format(jpeg):mode_rgb():quality(90)/discogs-images/R-4045049-1353401287-4409.jpeg.jpg");
        inTheNighsideEclipse.setLancamento(LocalDate.of(1994, 5, 22));
        inTheNighsideEclipse.setPreco(BigDecimal.valueOf(39.99));
        inTheNighsideEclipse.setDescricao("In The Nightside Eclipse é o primeiro álbum Oficial de estúdio da banda de Black metal Norueguesa, Emperor lançado em 1994 pela distribuidora Candlelight Records. Em seu encarte, há uma mensagem em memória de Euronymous, assassinado em 1993.\n" +
                "\n" +
                "Considerado um marco na cena black metal, o álbum foi classificado pelos críticos como um dos álbuns mais influentes do gênero. Ele também contém uma das faixas mais conhecidas da Banda, \"I Am the Black Wizards\". e ''Cosmic Keys to My Creations & Times\"");
        inTheNighsideEclipse.setBanda(emperor);

        Livro onlyDeathIsReal = new Livro();
        onlyDeathIsReal.setNome("Only Death Is Real");
        onlyDeathIsReal.setCapa("https://images-na.ssl-images-amazon.com/images/I/51rg8OF8aCL._SX392_BO1,204,203,200_.jpg");
        onlyDeathIsReal.setPaginas(288);
        onlyDeathIsReal.setAutor("Tom Gabriel Fischer");
        onlyDeathIsReal.setLancamento(LocalDate.of(2010, 3, 30));
        onlyDeathIsReal.setPreco(BigDecimal.valueOf(205.16));
        onlyDeathIsReal.setDescricao("This formidable oversized hardcover runs 288 pages (including a 32-page color section), and combines hundreds of unseen early Hellhammer and Celtic Frost photos with a vast treasure trove of artwork and memorabilia. A substantial written component by Fischer details his upbringing on the outskirts of Zurich, Switzerland, and the hardships and triumphs he faced bringing the visions of his groundbreaking bands Hellhammer and eventually Celtic Frost to reality. In addition, the book includes an introduction by Nocturno Culto of Norwegian black metal act Darkthrone, and a foreword by noted metal author Joel McIver.\n" +
                "\n" +
                "Without question Only Death Is Real goes farther than any other source in exploring the origins of underground heavy metal. The wealth of visual information is astounding, both in terms of documenting early 1980s headbangers and exposing the still-relevant imagery of the first Hellhammer and Celtic Frost photo sessions. On top of that, the written chapters combine Tom Fischer’s often shocking stories with lengthy quotes from Martin Eric Ain and the other main Hellhammer members, explaining in intimately human terms how extreme metal was born.");
        onlyDeathIsReal.setBanda(celticFrost);

        Album monotheist = new Album();
        monotheist.setNome("Monotheist");
        monotheist.setFormato(AlbumFormato.CASSETE);
        monotheist.setCapa("https://cdn.shopify.com/s/files/1/0664/5543/products/CELTICFROSTMONOTHEIST_1024x1024.jpg?v=1594157402");
        monotheist.setLancamento(LocalDate.of(2006, 5, 29));
        monotheist.setPreco(BigDecimal.valueOf(65.15));
        monotheist.setDescricao("Monotheist é o sexto é último álbum de estúdio da banda suíça de heavy metal Celtic Frost, lançado em 2006. Ele é a primeira gravação nova lançada em 14 anos pela banda após uma série de relançamentos.");
        monotheist.setBanda(celticFrost);

        Livro lobosQueForamHomens = new Livro();
        lobosQueForamHomens.setNome("Lobos que foram homens");
        lobosQueForamHomens.setCapa("https://images-na.ssl-images-amazon.com/images/I/51vrlPov-hL._SX346_BO1,204,203,200_.jpg");
        lobosQueForamHomens.setPaginas(448);
        lobosQueForamHomens.setAutor("Ricardo S. Amorim");
        lobosQueForamHomens.setLancamento(LocalDate.of(2021, 2, 15));
        lobosQueForamHomens.setPreco(BigDecimal.valueOf(159.90));
        lobosQueForamHomens.setDescricao("Com mais de vinte e cinco anos de carreira, o Moonspell é a banda de heavy metal mais importante de Portugal. Agora, sua história é contada pela primeira vez. Mais do que a simples biografia de uma banda, LOBOS QUE FORAM HOMENS disseca uma carreira feita de riscos e conquistas, revelando fatos até então inteiramente desconhecidos do público. Com depoimentos de todos os seus atuais e antigos membros, bem como de diversos colaboradores e membros de outras bandas de referência, o livro apresenta a história contada sem filtros, com todos os ossos à mostra. Adentrando no círculo íntimo do Moonspell, o autor explora seus sucessos e tribulações, com o foco direcionado para o lado pessoal e humano de suas relações, que nem sempre foram fáceis, tornando LOBOS QUE FORAM HOMENS um retrato essencial para compreender a importância desse fenômeno do metal europeu.");
        lobosQueForamHomens.setBanda(moonspell);

        produtoService.saveAll(Arrays.asList(melissa, dontBreakTheOath, sadWingsOfDestiny, painkiller, firepower,
                evangelicis, demigod, leprosy, inTheNighsideEclipse, onlyDeathIsReal, monotheist, lobosQueForamHomens));

//        USUÁRIOS ---------------------------------------------------------------------------------------------------

        Usuario usuario = new Usuario();
        usuario.setNome("Admin");
        usuario.setEmail("lhcdiscos@gmail.com");
        usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
        usuario.addRoles(Role.ROLE_ADMIN, Role.ROLE_USER);

        usuarioService.save(usuario);

//        PEDIDOS ----------------------------------------------------------------------------------------------------
        Pedido pedido1 = new Pedido();
        ItemPedido itemPedido1 = new ItemPedido(onlyDeathIsReal, 2);
        itemPedido1.setPedido(pedido1);
        ItemPedido itemPedido2 = new ItemPedido(evangelicis, 1);
        itemPedido2.setPedido(pedido1);
        pedido1.addItens(itemPedido1, itemPedido2);
        pedido1.setCliente(usuario);
        pedido1.setData(LocalDateTime.of(2021,1,24,22,5,30));
        BigDecimal total = BigDecimal.ZERO;
        for (ItemPedido item : pedido1.getItens()){
            total = total.add(item.getValorUnitario());
        }
        DadosPagamento dadosPagamento1 = new DadosPagamento();
        dadosPagamento1.setValorPago(total);
        dadosPagamento1.setData(LocalDateTime.of(2021,1,24,22,5,30));
        dadosPagamento1.setStripeId("xxxxxxx");
        dadosPagamento1.setStripeStatus("pedidoteste");
        pedido1.setPagamento(dadosPagamento1);


        Pedido pedido2 = new Pedido();
        ItemPedido itemPedido3 = new ItemPedido(onlyDeathIsReal, 1);
        itemPedido3.setPedido(pedido2);
        ItemPedido itemPedido4 = new ItemPedido(demigod, 1);
        itemPedido4.setPedido(pedido2);
        pedido2.addItens(itemPedido3, itemPedido4);
        pedido2.setCliente(usuario);
        pedido2.setData(LocalDateTime.of(2021,2,24,22,5,30));
        total = BigDecimal.ZERO;
        for (ItemPedido item : pedido2.getItens()){
            total = total.add(item.getValorUnitario());
        }
        DadosPagamento dadosPagamento2 = new DadosPagamento();
        dadosPagamento2.setValorPago(total);
        dadosPagamento2.setData(LocalDateTime.of(2021,2,24,22,5,30));
        dadosPagamento2.setStripeId("xxxxdfsdfxxx");
        dadosPagamento2.setStripeStatus("pedidoteste");
        pedido2.setPagamento(dadosPagamento2);

        Pedido pedido3 = new Pedido();
        ItemPedido itemPedido5 = new ItemPedido(onlyDeathIsReal, 1);
        itemPedido5.setPedido(pedido3);
        ItemPedido itemPedido6 = new ItemPedido(lobosQueForamHomens, 1);
        itemPedido6.setPedido(pedido3);
        pedido3.addItens(itemPedido5, itemPedido6);
        pedido3.setCliente(usuario);
        pedido3.setData(LocalDateTime.of(2021,2,26,22,5,30));
        total = BigDecimal.ZERO;
        for (ItemPedido item : pedido3.getItens()){
            total = total.add(item.getValorUnitario());
        }
        DadosPagamento dadosPagamento3 = new DadosPagamento();
        dadosPagamento3.setValorPago(total);
        dadosPagamento3.setData(LocalDateTime.of(2021,2,26,22,5,30));
        dadosPagamento3.setStripeId("xxxx15dfsdfxxx");
        dadosPagamento3.setStripeStatus("pedidoteste");
        pedido3.setPagamento(dadosPagamento3);



        pedidoService.saveAll(Arrays.asList(pedido1, pedido2, pedido3));

    }
}
