package com.luiz.lhcdiscos.service;

import com.luiz.lhcdiscos.model.entity.*;
import com.luiz.lhcdiscos.model.enums.AlbumFormato;
import com.luiz.lhcdiscos.model.enums.CamisetaTamanho;
import com.luiz.lhcdiscos.model.enums.Genero;
import com.luiz.lhcdiscos.model.enums.Role;
import com.luiz.lhcdiscos.repository.BandaRepository;
import com.luiz.lhcdiscos.repository.PedidoRepository;
import com.luiz.lhcdiscos.repository.ProdutoRepository;
import com.luiz.lhcdiscos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ProdutoRepository<Produto> produtoRepository;

    @Autowired
    private BandaRepository bandaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public void instanciaBancoDeDadosDeTeste() {

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
        Banda enslaved = new Banda("Enslaved", Genero.BLACKMETAL);

        Banda candlemass = new Banda("Candlemass", Genero.DOOMMETAL);
        Banda saintvitus = new Banda("Saint Vitus", Genero.DOOMMETAL);

        Banda carnifex = new Banda("Carnifex", Genero.DEATHCORE);
        Banda thyArtIsMurder = new Banda("Thy Art Is Murder", Genero.DEATHCORE);

        Banda petShopBoys = new Banda("Pet Shop Boys", Genero.OUTROS);
        Banda depecheMode = new Banda("Baby Metal", Genero.OUTROS);

        bandaRepository.saveAll(Arrays.asList(mercyfulFate, judasPriest, behemoth, death, emperor, moonspell, paradiseLost,
                theatreOfTragedy, megadeth, sepultura, darkFortress, mgla, darkthrone, candlemass, saintvitus,
                carnifex, thyArtIsMurder, petShopBoys, depecheMode, celticFrost, enslaved));

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
        evangelicis.setTamanho(CamisetaTamanho.M);
        evangelicis.setCapa("https://behemoth-store.com/eng_pm_Evangelicis-Behemoth-all-over-print-t-shirt-785_1.jpg");
        evangelicis.setLancamento(LocalDate.of(2018, 10, 30));
        evangelicis.setPreco(BigDecimal.valueOf(60));
        evangelicis.setDescricao("An all-over print 'Evangelicis' Behemoth t-shirt. \n" +
                "\n" +
                "The print is made with a sublimation technique, is impalpable and in full range of colours. It also won't wash out. The material is of a highest quality 100% polyester fabrics , same one which is used for clothing for sports activities. It has a very nice feeling, absorbs moisture and is fully breathable. ");
        evangelicis.setBanda(behemoth);

        Camiseta demigod = new Camiseta();
        demigod.setNome("Demigod");
        demigod.setTamanho(CamisetaTamanho.G);
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

        Album utgard = new Album();
        utgard.setNome("Utgard");
        utgard.setFormato(AlbumFormato.CD);
        utgard.setCapa("https://e.snmc.io/i/600/w/978bd974c18ef8417cf8f46345d210c0/8346050/enslaved-utgard-Cover-Art.jpg");
        utgard.setLancamento(LocalDate.of(2020, 10, 2));
        utgard.setPreco(BigDecimal.valueOf(45.50));
        utgard.setDescricao("O ENSLAVED vem cuidadosamente se reinventando a cada álbum, refinando e melhorando sua criatividade, levando seu Black Metal pagão e progressivo a um nível totalmente único. “A época do álbum E [14º álbum de estúdio, lançado em 2017] foi a mais intensa que tivemos até agora”, comenta com entusiasmo o guitarrista Ivar Bjørnson. “Cobrimos mais territórios do que nunca e, com alegria, percebemos que éramos mais notados fora da cena do metal extremo”. Ele lembra que foi uma época emocionante mas, ao mesmo tempo, estranha porque um dia eles estavam tocando seu revolucionário álbum “Frost” na íntegra apenas para no dia seguinte, tocar suas músicas mais psicodélicas em um festival progressivo. Só agora ficou claro que este foi apenas o começo de mais uma jornada.\n" +
                "\n" +
                "E é assim que chegamos ao novo e 15º álbum de estúdio “Utgard”. “Utgard pode ser inúmeras coisas”, diz o vocalista Grutle Kjellson. “Uma imagem, uma metáfora, uma localização esotérica. Na mitologia nórdica, a conhecemos como uma paisagem onde vivem os gigantes, onde os deuses de Asgard não tem nenhum controle. A nível psicológico, pode representar as esferas do sono, sonhos ou sonhos lúcidos; as fronteiras externas da sua própria consciência e o que está além delas no subconsciente. Utgard representa algo difícil de dominar completamente e até talvez impossível. Esse algo é perigoso, caótico, incontrolável. No entanto, há algo encantador em um lugar como este. É onde habitam a loucura, a criatividade, o humor e o caos”.\n" +
                "\n" +
                "“Utgard pode ser um lugar muito assustador, mas é exatamente disso que trata o álbum”, acrescenta Grutle. “Vencer o medo e conviver com os seres encontrados neste lugar”.");
        utgard.setBanda(enslaved);


        Livro lobosQueForamHomens = new Livro();
        lobosQueForamHomens.setNome("Lobos que foram homens");
        lobosQueForamHomens.setCapa("https://images-na.ssl-images-amazon.com/images/I/51vrlPov-hL._SX346_BO1,204,203,200_.jpg");
        lobosQueForamHomens.setPaginas(448);
        lobosQueForamHomens.setAutor("Ricardo S. Amorim");
        lobosQueForamHomens.setLancamento(LocalDate.of(2021, 2, 15));
        lobosQueForamHomens.setPreco(BigDecimal.valueOf(159.90));
        lobosQueForamHomens.setDescricao("Com mais de vinte e cinco anos de carreira, o Moonspell é a banda de heavy metal mais importante de Portugal. Agora, sua história é contada pela primeira vez. Mais do que a simples biografia de uma banda, LOBOS QUE FORAM HOMENS disseca uma carreira feita de riscos e conquistas, revelando fatos até então inteiramente desconhecidos do público. Com depoimentos de todos os seus atuais e antigos membros, bem como de diversos colaboradores e membros de outras bandas de referência, o livro apresenta a história contada sem filtros, com todos os ossos à mostra. Adentrando no círculo íntimo do Moonspell, o autor explora seus sucessos e tribulações, com o foco direcionado para o lado pessoal e humano de suas relações, que nem sempre foram fáceis, tornando LOBOS QUE FORAM HOMENS um retrato essencial para compreender a importância desse fenômeno do metal europeu.");
        lobosQueForamHomens.setBanda(moonspell);

        Album hermitage = new Album();
        hermitage.setNome("Hermitage");
        hermitage.setFormato(AlbumFormato.VINIL);
        hermitage.setCapa("https://e.snmc.io/i/600/w/94385ac71f9d2d8f6e6a360a5b14f1d2/8586029/moonspell-hermitage-Cover-Art.jpg");
        hermitage.setLancamento(LocalDate.of(2021, 2, 26));
        hermitage.setPreco(BigDecimal.valueOf(119.90));
        hermitage.setDescricao("MOONSPELL is approaching their 30th anniversary as a band more ambitiously and stronger than ever. Hermitage – the gothic metal pioneers‘13th studio album – was recorded, mixed and mastered by Jaime Gomez Arellano (Paradise Lost, Primordial, Ghost, Sólstafir, etc.) at the Orgone Studios in the UK. Building upon the album’s dark, revolutionary and sensitive facets, it’s an entertaining yet epic journey through the darkest days of human existence. Beautifully crafted tracks such as “The Greater Good“ show us the modernity of the wolves in 2021, while “Common Prayers“ embraces the MOONSPELL tradition of writing a dark, gothic metal song like no other band can. “All Or Nothing“ brings back the ethereal and melodic sounds of MOONSPELL, served by a beautiful musical landscape, making it most likely one of the deepest and most sincerely heart-breaking tunes the Portuguese dark metal titans have ever written. But of course, they don‘t stray away from their metal roots, especially influenced by underground metal bands like Bathory on tracks like the bombastic and epic metal anthem “The Hermit Saints“! Hermitage is a revolutionary, wonderfully intuitive and epic journey by one of the most distinctive dark metal bands of all time, and a testament to what they’ve always loved the most: Honest, emotional metal that binds us even in the darkest times.");
        hermitage.setBanda(moonspell);

        Album obsidian = new Album();
        obsidian.setNome("Obsidian");
        obsidian.setFormato(AlbumFormato.CD);
        obsidian.setCapa("https://e.snmc.io/i/600/w/80108bf1dd88c4e53395a97416193ac2/8181307/paradise-lost-obsidian-Cover-Art.jpg");
        obsidian.setLancamento(LocalDate.of(2020, 5, 15));
        obsidian.setPreco(BigDecimal.valueOf(50.00));
        obsidian.setDescricao("Produzido pela própria banda, com a ajuda do renomado guru de estúdio Jamie \"Gomez\" Arellano, \"Obsidian\" é outra amostra da ardente paixão do PARADISE LOST por continuar em frente, sempre. Os fãs da banda que preferem algo mais feroz e mais raiz, se sentiram bem servidos com músicas como ?The Devil Embraced? e ?Serenity?, enquanto que os fãs mais góticos serão instantaneamente hipnotizados por faixas como ?Ghosts?, ?Forsaken? e ?Ending Days?. Entre esses dois extremos, temos um PARADISE LOST que continua desafiando os limites da sua identidade musical há muito estabelecida, resultando na criação de algumas das músicas mais ousadas de sua carreira até hoje.\n" +
                "\n" +
                "E como sempre, para defender a mística de sua banda, Holmes continua relutante em revelar os detalhes de suas letras, mas \"Obsidian\" é claramente um álbum com muitas coisas preocupantes que não saem da sua cabeça. \"Não há um tema central. É exatamente como me sentia na época\", explica Nick. \"Se houver uma ideia ou um conceito por trás disso, é a ideia de que certas decisões podem afetar você e sua vida muito tempo depois, como acontece com o Efeito Borboleta. Eu amo esse conceito de você fazer algo hoje que possa afetar algo mais tarde. [...]\"\n" +
                "\n" +
                "Com um dos materiais mais poderosos de sua banda, Nick Holmes utilizou uma variedade de conceitos líricos e declarações misteriosas em \"Obsidian\" e segundo Greg Mackintosh manter esse ar de mistério é uma parte fundamental da identidade do PARADISE LOST. Enquanto eles marcham para sua quarta década como banda, o PARADISE LOST nunca soou mais potente ou unido em sua determinação de dar vida à escuridão. \"Obsidian\" é mais um ponto alto na carreira da banda, assim como os álbuns \"The Plague Within\" e \"Medusa\", mas superando-os artisticamente em profundidade, clima e poder emocional.\n" +
                "\n" +
                "Enquanto que muitas outras bandas do estilo abraçam o negócio da nostalgia, o PARADISE LOST continua sendo uma força vibrante, vital e infinitamente elegante dentro do gênero.");
        obsidian.setBanda(paradiseLost);

        Album draconianTimes = new Album();
        draconianTimes.setNome("Draconian Times");
        draconianTimes.setFormato(AlbumFormato.CD);
        draconianTimes.setCapa("https://e.snmc.io/i/600/w/6689efc3ca7d75cce5097d406a52c290/1215286/paradise-lost-draconian-times-Cover-Art.jpg");
        draconianTimes.setLancamento(LocalDate.of(1995, 6, 12));
        draconianTimes.setPreco(BigDecimal.valueOf(39.90));
        draconianTimes.setDescricao("Draconian Times is the fifth studio album by British metal band Paradise Lost. Two tracks from the album, \"The Last Time\" and \"Forever Failure\", were released as singles with music videos, and both charted.\n" +
                "\n" +
                "The album was played in its entirety on the band's live record Draconian Times MMXI. It was also released with Shades of God and Icon in a boxed set called Original Album Classics.\n" +
                "\n" +
                "A song called \"Another Desire\" was written during the recording of Draconian Times, but not released on the album or the reissues. Instead, it was included in the \"Forever Failure\" single.");
        draconianTimes.setBanda(paradiseLost);

        Album quadra = new Album();
        quadra.setNome("Quadra");
        quadra.setFormato(AlbumFormato.CD);
        quadra.setCapa("https://e.snmc.io/i/600/w/0e1086ec99427554e83e43c349cb56f2/7980491/sepultura-quadra-Cover-Art.jpg");
        quadra.setLancamento(LocalDate.of(2020, 2, 7));
        quadra.setPreco(BigDecimal.valueOf(30.00));
        quadra.setDescricao("Quadra é o décimo quinto álbum de estúdio da banda brasileira Sepultura, lançado em 7 de fevereiro de 2020, através da gravadora Nuclear Blast. É um álbum conceitual baseado na numerologia, no número \"quatro\" e seus significados como descrito no Quadrívio. Assim como em seu álbum anterior Machine Messiah, a banda foi à Suécia para gravar com o produtor Jens Bogren.\n" +
                "\n" +
                "É o álbum mais bem sucedido da banda desde Against de 1998, entrando nas paradas musicais em 17 países, e no top 20 em 7 países. É também o álbum mais bem sucedido da história da banda na Alemanha e Suíça, ultrapassando a posição de Roots, atingindo a posição número 5 e número 13, respectivamente.");
        quadra.setBanda(sepultura);

        Album thatOneNight = new Album();
        thatOneNight.setNome("That One Night: Live In Buenos Aires");
        thatOneNight.setFormato(AlbumFormato.DVD);
        thatOneNight.setCapa("https://images-americanas.b2w.io/produtos/01/00/img/2448951/4/2448951496_1GG.jpg");
        thatOneNight.setLancamento(LocalDate.of(2007, 3, 6));
        thatOneNight.setPreco(BigDecimal.valueOf(30.00));
        thatOneNight.setDescricao("DVD lançado em 2007 pela banda Americana de Thrash Metal. Gravado ao vivo no Obras Stadium em Buenos Aires, Argentina, em 9 de outubro de 2005.\n" +
                "\n");
        thatOneNight.setBanda(megadeth);

        Album spectresFromTheOldWorld = new Album();
        spectresFromTheOldWorld.setNome("Spectres From the Old World");
        spectresFromTheOldWorld.setFormato(AlbumFormato.VINIL);
        spectresFromTheOldWorld.setCapa("https://e.snmc.io/i/600/w/bc1ed76c7d8ae6ab1c508e2a88a8af1b/8138727/dark-fortress-spectres-from-the-old-world-Cover-Art.jpg");
        spectresFromTheOldWorld.setLancamento(LocalDate.of(2020, 2, 28));
        spectresFromTheOldWorld.setPreco(BigDecimal.valueOf(119.00));
        spectresFromTheOldWorld.setDescricao("Venerated black metal specialists DARK FORTRESS return with a new album, Spectres from the Old World ! After a six-year gap, Germany s darkest sons continue where Venereal Dawn (2014) left off conceptually the birth-death lifecycle of the universe as told by spacetime but are expanding DARK FORTRESS musical horizons by descending faster and with spiteful intent into the cosmic abyss. Indeed, Spectres from the Old World is more direct, more aggressive than its predecessors. These musical themes play out fiercely in Coalescence, The Spider in the Web, Pazuzu, and the pivotal title track. Here, DARK FORTRESS prove that they remain committed to black metal s netherworld spark. The malignant sounds that inspired DARK FORTRESS on Tales from Eternal Dusk (2001), Seance (2006), and Ylem (2010) are still very much part of the DNA that makes up the band s finest hour on Spectres from the Old World. But as DARK FORTRESS find themselves in good black-hearted company, their destiny calls for venturing beyond the confines of the genre. Pali Aike, named after an actual Chilean hellscape, finds vocalist Morean marshalling his bandmates to very center of time-worn volcanoes to an epic thrum. Isa, in many ways the opposite of Pali Aike, feels very cold, its dissonance and grooves inspired by antediluvian ice storms. The triumvirate is complete with the limpid black expanse of Swan Song. At no time in DARK FORTRESS history have they reached so far out into the darkness, only to find the domine of astronomy non-existent, the end merely an end. Recorded, engineered, mixed, and mastered by V. Santura at Woodshed Studios throughout 2019, Spectres from the Old World marks an important milestone in DARK FORTRESS journey along actual and spiritual left-hand paths. Glimpse into the endless black of the universe on Spectres from the Old World.");
        spectresFromTheOldWorld.setBanda(darkFortress);

        Album exercisesInFutility = new Album();
        exercisesInFutility.setNome("Exercises in Futility");
        exercisesInFutility.setFormato(AlbumFormato.VINIL);
        exercisesInFutility.setCapa("https://e.snmc.io/i/600/w/09d6bfba01a5aedae12c7e96161a3d45/5811138/mgla-exercises-in-futility-Cover-Art.jpg");
        exercisesInFutility.setLancamento(LocalDate.of(2015, 9, 4));
        exercisesInFutility.setPreco(BigDecimal.valueOf(115.99));
        exercisesInFutility.setDescricao("Exercises in Futility is the third studio album by Polish black metal band Mgła. It was released on 4 September 2015, through Northern Heritage Records. Featuring \"a raw, melodic black metal\" style that was compared to those of Watain and early Burzum, the album is regarded as \"the biggest and most impactful release of the band's 15-year career.\" The front cover features an artwork from French engraver Marcel Roux, titled L'aveugle (\"The blind\", 1908).");
        exercisesInFutility.setBanda(mgla);

        Album aBlazeInTheNorthernSky = new Album();
        aBlazeInTheNorthernSky.setNome("A Blaze in the Northern Sky");
        aBlazeInTheNorthernSky.setFormato(AlbumFormato.CASSETE);
        aBlazeInTheNorthernSky.setCapa("https://e.snmc.io/i/600/w/dcf488f0f17108758a72174b50c64592/5706952/darkthrone-a-blaze-in-the-northern-sky-Cover-Art.jpg");
        aBlazeInTheNorthernSky.setLancamento(LocalDate.of(1992, 2, 26));
        aBlazeInTheNorthernSky.setPreco(BigDecimal.valueOf(89.99));
        aBlazeInTheNorthernSky.setDescricao("A Blaze In The Northern Sky (em português: Uma chama no céu do Norte) é o segundo disco do grupo musical Darkthrone, tendo sido lançado em fevereiro de 1992 pela gravadora da Inglaterra Peaceville. É o marco do início da fase black metal da banda.\n \n Foi gravado em agosto de 1991 no estúdio Creative, o mesmo estúdio que a banda Mayhem gravou o LP Deathcrush (em 1987) - considerado outro clássico do gênero Black Metal.");
        aBlazeInTheNorthernSky.setBanda(darkthrone);

        Album epicusDoominicusMetallicus = new Album();
        epicusDoominicusMetallicus.setNome("Epicus Doomicus Metallicus");
        epicusDoominicusMetallicus.setFormato(AlbumFormato.VINIL);
        epicusDoominicusMetallicus.setCapa("https://e.snmc.io/i/600/w/4dfc471de8954f01b9274ee0f816f1ce/2777656/candlemass-epicus-doomicus-metallicus-Cover-Art.jpg");
        epicusDoominicusMetallicus.setLancamento(LocalDate.of(1986, 2, 10));
        epicusDoominicusMetallicus.setPreco(BigDecimal.valueOf(199.99));
        epicusDoominicusMetallicus.setDescricao("Epicus Doomicus Metallicus é o álbum de estreia da banda sueca de doom metal Candlemass, lançado em 10 de junho de 1986 pela Black Dragon Records. O álbum tinha um som muito diferente das outras bandas de heavy metal da Europa da época, por causa de seu uso de vocais operísticos misturados com \u200B\u200Briffs de guitarra lentos e pesados. O álbum não vendeu bem em sua versão inicial, o que levou o grupo a ser retirado da gravadora durante o mesmo ano. Desde então, o álbum foi relançado em vários formatos diferentes. O título Epicus Doomicus Metallicus é uma adaptação do latim para Epic Doom Metal - o gênero do qual a banda é creditada por serem pioneiros.");
        epicusDoominicusMetallicus.setBanda(candlemass);

        Album bornTooLate = new Album();
        bornTooLate.setNome("Born Too Late");
        bornTooLate.setFormato(AlbumFormato.CD);
        bornTooLate.setCapa("https://e.snmc.io/i/600/w/818a35eee44dfe396de86432e2c43894/2700539/saint-vitus-born-too-late-Cover-Art.jpg");
        bornTooLate.setLancamento(LocalDate.of(1986, 10, 10));
        bornTooLate.setPreco(BigDecimal.valueOf(49.99));
        bornTooLate.setDescricao("Born Too Late is the third studio album by the American doom metal band Saint Vitus, which was released in 1986. It was the first Saint Vitus album featuring The Obsessed singer Scott \"Wino\" Weinrich. It is generally cited as their greatest effort.");
        bornTooLate.setBanda(saintvitus);

        Camiseta sisterRot = new Camiseta();
        sisterRot.setNome("Sister Rot");
        sisterRot.setTamanho(CamisetaTamanho.P);
        sisterRot.setCapa("https://proassets.monopile.cloud/94671/2c1ac8d6d1d64b05fc6a7a3466093396.jpg");
        sisterRot.setLancamento(LocalDate.of(2021, 2, 15));
        sisterRot.setPreco(BigDecimal.valueOf(79.99));
        sisterRot.setDescricao("A black 'Sister Rot' Carnifex t-shirt. Print on the front and back.");
        sisterRot.setBanda(carnifex);

        Album holyWar = new Album();
        holyWar.setNome("Holy War");
        holyWar.setFormato(AlbumFormato.CD);
        holyWar.setCapa("https://e.snmc.io/i/600/w/4cb39de69291708fb37cc025e504a444/6462030/thy-art-is-murder-holy-war-Cover-Art.png");
        holyWar.setLancamento(LocalDate.of(2015, 6, 30));
        holyWar.setPreco(BigDecimal.valueOf(49.99));
        holyWar.setDescricao("Holy War is the third studio album by Australian deathcore band Thy Art Is Murder. It was released on June 26 through UNFD and Nuclear Blast. The album was produced and mixed by Will Putney, recorded in late 2014. Music videos were released for the tracks \"Light Bearer\" on May 29, and for \"Holy War\" on June 29. Holy War had a successful first week of sales, charting at No. 7 in Australia and No. 82 in the U.S. as well as several other U.S. Charts.");
        holyWar.setBanda(thyArtIsMurder);

        Album behaviour = new Album();
        behaviour.setNome("Holy War");
        behaviour.setFormato(AlbumFormato.VINIL);
        behaviour.setCapa("https://e.snmc.io/i/600/w/c02c71aae254bc4fa582a6b2ee647e14/1466918/pet-shop-boys-behaviour-Cover-Art.jpg");
        behaviour.setLancamento(LocalDate.of(1990, 10, 22));
        behaviour.setPreco(BigDecimal.valueOf(149.99));
        behaviour.setDescricao("Behaviour é o quarto álbum de estúdio da banda Pet Shop Boys, lançado a 22 de Outubro de 1990.\n" +
                "\n" +
                "Behaviour foi aclamado por criticos desde o inicio por ser diferente de tudo o que até então os Pet Shop Boys faziam, os fãs rejeitaram o álbum no começo, mas o inconfundivel estilo Introspectivo e Melancólico do álbum fez com que todos o aclamassem.\n" +
                "\n" +
                "O disco pertence à lista dos 1001 Albums You Must Hear Before You Die. O disco atingiu o nº 45 da Billboard 200.");
        behaviour.setBanda(petShopBoys);

        Album musicForTheMasses = new Album();
        musicForTheMasses.setNome("Music for the Masses");
        musicForTheMasses.setFormato(AlbumFormato.VINIL);
        musicForTheMasses.setCapa("https://e.snmc.io/i/600/w/bea182894ada5379488c582f3a6e3ec0/1692224/depeche-mode-music-for-the-masses-Cover-Art.jpg");
        musicForTheMasses.setLancamento(LocalDate.of(1987, 9, 28));
        musicForTheMasses.setPreco(BigDecimal.valueOf(149.99));
        musicForTheMasses.setDescricao("Music for the Masses is the sixth studio album by English electronic music band Depeche Mode. It was released on 28 September 1987 by Mute Records. The album was supported by the Music for the Masses Tour.");
        musicForTheMasses.setBanda(depecheMode);


        produtoRepository.saveAll(Arrays.asList(melissa, dontBreakTheOath, sadWingsOfDestiny, painkiller, firepower,
                evangelicis, demigod, leprosy, inTheNighsideEclipse, onlyDeathIsReal, monotheist, utgard, lobosQueForamHomens,
                obsidian, quadra, hermitage, draconianTimes, thatOneNight, spectresFromTheOldWorld, exercisesInFutility,
                aBlazeInTheNorthernSky, epicusDoominicusMetallicus, bornTooLate, sisterRot, holyWar, behaviour,
                musicForTheMasses));

//        USUÁRIOS ---------------------------------------------------------------------------------------------------

        Usuario usuario = new Usuario();
        usuario.setNome("Admin");
        usuario.setEmail("lhcdiscos@gmail.com");
        usuario.setSenha("$2y$12$ZIWGwavDCURUmjTVWqRbd.aURIgt/PlO98Lr7fhjR/Zpg9ZhCZwe.");
        usuario.addRoles(Role.ROLE_ADMIN, Role.ROLE_USER);

        usuarioRepository.save(usuario);

//        PEDIDOS ----------------------------------------------------------------------------------------------------
        Pedido pedido1 = new Pedido();
        ItemPedido itemPedido1 = new ItemPedido(onlyDeathIsReal, 2);
        itemPedido1.setPedido(pedido1);
        ItemPedido itemPedido2 = new ItemPedido(evangelicis, 1);
        itemPedido2.setPedido(pedido1);
        pedido1.addItens(Arrays.asList(itemPedido1, itemPedido2));
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
        pedido2.addItens(Arrays.asList(itemPedido3, itemPedido4));
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
        pedido3.addItens(Arrays.asList(itemPedido5, itemPedido6));
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


        pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));

    }

}
