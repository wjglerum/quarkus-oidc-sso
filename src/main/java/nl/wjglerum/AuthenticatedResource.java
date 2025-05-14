package nl.wjglerum;

import io.quarkus.oidc.UserInfo;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/authenticated")
@Authenticated
public class AuthenticatedResource {

    @Inject
    UserInfo userInfo;

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    public String authenticated() {
        return securityIdentity.getPrincipal().getName();
    }

    @GET
    @Path("userinfo")
    public String userInfo() {
        return userInfo.getUserInfoString();
    }

    @GET
    @Path("admin")
    @RolesAllowed("admin")
    public String admin() {
        return securityIdentity.getRoles().toString();
    }
}
