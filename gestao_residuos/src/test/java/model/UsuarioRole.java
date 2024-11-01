package model;

public enum UsuarioRole {
    ADMIN("admin"),
    GERENTE("gerente"),
    MOTORISTA("motorista");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}

