package rest_accreditation.config;

public class AuthenticationResponse {
    private String code;
    private String jwt;

    public AuthenticationResponse(String code,String jwt) {
        this.code = code;
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}