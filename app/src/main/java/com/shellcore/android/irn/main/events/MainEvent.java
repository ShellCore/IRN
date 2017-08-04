package com.shellcore.android.irn.main.events;

/**
 * Created by Cesar on 01/08/2017.
 */

public class MainEvent {

    public static final int QUERY_ERROR = 1; // No se encontraron datos en base de datos
    public static final int VALIDATION_ERROR = 2; // Los datos en base de datos no están vigentes
    public static final int DOWNLOAD_ERROR = 3; // Error en la actualización de la base de datos
    public static final int SUCCESS = 4; // Las tablas se actualizaron correctamente
    public static final int VALIDATION_SUCCESS = 5; // Las tablas actuales siguen siendo válidas

    private int type;
    private String error;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
