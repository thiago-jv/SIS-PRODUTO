package crud.com.infra.utils;

import java.io.Serializable;

public class Messages implements Serializable {
    
    public static final String MSG_CATEGORIA_EM_USO = "Categoria de código %d não pode ser removida, pois está em uso.";

    private Messages() {
    }
}

