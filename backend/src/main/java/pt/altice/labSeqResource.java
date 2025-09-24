package pt.altice;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigInteger;

@Path("/labseq")
public class labSeqResource {

    @Inject
    Service labSecService;

    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(@PathParam("n") int n) {

        // DTO for a well structured response
        try {

            if (n < 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Index must be greater or equal to zero")
                    .build();
            }

            BigInteger response = labSecService.calcLabSeq(n);
            return Response.ok(response).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
