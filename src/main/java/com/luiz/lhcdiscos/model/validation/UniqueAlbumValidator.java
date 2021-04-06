package com.luiz.lhcdiscos.model.validation;

import com.luiz.lhcdiscos.model.entity.Album;
import com.luiz.lhcdiscos.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueAlbumValidator implements ConstraintValidator<UniqueAlbum, Album> {

    @Autowired
    private AlbumService albumService;

    @Override
    public void initialize(UniqueAlbum constraintAnnotation) {
    }

    @Override
    public boolean isValid(Album album, ConstraintValidatorContext constraintValidatorContext) {

//        Checa se o álbum é novo ou é uma atualização de um já salvo no BD
        if (album.getId() == null) {
            return albumService.estaDisponivelParaPersistir(album);
        }

        Album albumNoBd = albumService.buscaPorId(album.getId());

//        Checa se o ábum salvo no BD é o mesmo que se quer atualizar. Caso não seja, checa se há outro
//        álbum similar, para evitar que sejam cadastrados álbuns duplicados
        if (albumNoBd.getNome().equalsIgnoreCase(album.getNome())
            && (albumNoBd.getFormato().equals(album.getFormato()))
            && (albumNoBd.getBanda().equals(album.getBanda()))) {
            return true;
        } else {
            return albumService.estaDisponivelParaPersistir(album);
        }

    }

}
