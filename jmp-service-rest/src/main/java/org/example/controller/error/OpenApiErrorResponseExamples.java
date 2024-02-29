package org.example.controller.error;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class OpenApiErrorResponseExamples {

    public static final String INTERNAL_SERVER_ERROR_EXAMPLE =
            """
                    {
                      "errors": [
                        {
                          "type": "/internal-server-error",
                          "title": "Unexpected internal server error",
                          "status": 500,
                          "detail": "Unexpected internal server error occurred. Please contact service administrator for more details."
                        }
                      ]
                    }
                    """;

    public static final String EXTERNAL_SERVER_ERROR_EXAMPLE =
            """
            {
               "errors": [
                 {
                   "type": "/external-server-error",
                   "title": "Unexpected external error",
                   "status": 500,
                   "detail": "Unexpected external server error occurred. Please contact service administrator for more details.",
                   "source": "CommerceTools"
                 }
               ]
             }
            """;

}