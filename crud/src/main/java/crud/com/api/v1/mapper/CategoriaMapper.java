package crud.com.api.v1.mapper;

import crud.com.api.v1.dto.categoria.CategoriaPostDTO;
import crud.com.api.v1.dto.categoria.CategoriaPutDTO;
import crud.com.api.v1.dto.categoria.CategoriaResponseDTO;
import crud.com.domain.model.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria toCategoria(CategoriaPostDTO categoriaPostDTO);

    Categoria toCategoria(CategoriaPutDTO categoriaPutDTO);

    CategoriaResponseDTO toCategoriaResponseDTO(Categoria categoria);

    List<CategoriaResponseDTO> toListCategoriaResponseDTO(List<Categoria> categoria);

}
