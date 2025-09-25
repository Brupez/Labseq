package pt.altice;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.math.BigInteger;

@Path("/labseq")
public class labSeqResource {

    @Inject
    Service labSecService;

    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get the n-th term of the Lab sequence",
            description = "Returns the n-th term of the Lab sequence."
    )
    @Parameter(
            name = "n",
            description = "Index must be equal or greater than 0)",
            required = true
    )
    @APIResponse(
            responseCode = "200",
            description = "The n-th term of the Lab sequence",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BigInteger.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid input (n < 0)",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = String.class)
            )
    )

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
