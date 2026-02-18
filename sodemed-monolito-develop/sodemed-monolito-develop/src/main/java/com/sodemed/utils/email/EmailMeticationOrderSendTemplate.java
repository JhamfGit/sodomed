package com.sodemed.utils.email;

public class EmailMeticationOrderSendTemplate {

  public static String getTemplate(String titulo, String userName, String eff_date, String orderNumber, String details,String totalPending, String detailsPending, boolean partial) {
    return String.format(
                """
                        <!DOCTYPE html>
                    <html lang="es">

                    <head>
                      <meta charset="UTF-8" />
                      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                      <title>Notificación de Pedido</title>
                      <style>
                        body {
                          font-family: Arial, sans-serif;
                          background-color: #f4f4f4;
                          margin: 0;
                          padding: 0;
                        }

                        .container {
                          max-width: 600px;
                          margin: 40px auto;
                          background-color: #fff;
                          padding: 30px;
                          border-radius: 10px;
                          box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                        }

                        .header {
                          text-align: center;
                          margin-bottom: 30px;
                        }

                        .logo {
                          max-width: 120px;
                          margin-bottom: 10px;
                        }

                        h1 {
                          color: #4c6aaf;
                          font-size: 26px;
                          margin-bottom: 10px;
                        }

                        h2 {
                          color: #333;
                          font-size: 20px;
                          margin-top: 30px;
                        }

                        p {
                          font-size: 16px;
                          color: #555;
                          line-height: 1.6;
                          margin-bottom: 20px;
                        }

                        .footer {
                          text-align: center;
                          font-size: 12px;
                          color: #777;
                          margin-top: 30px;
                          border-top: 1px solid #ddd;
                          padding-top: 15px;
                        }

                        .footer a {
                          color: #4c6aaf;
                          text-decoration: none;
                        }

                        .info {
                          background-color: #f9f9f9;
                          padding: 15px;
                          border: 1px solid #ddd;
                          border-radius: 5px;
                          margin-bottom: 20px;
                        }
                      </style>
                    </head>

                    <body>
                      <div class="container">
                        <div class="header">
                          <h1>%s</h1> <!-- Título dinámico -->
                        </div>
                        <p>Hola <strong>%s</strong>,</p> <!-- Nombre de la persona que realiza la solicitud -->

                        <h2>Detalles de tu solicitud</h2>
                        <div class="info">
                          <p><strong>Fecha:</strong> %s</p> <!-- Número de la orden -->
                          <p><strong>Número de Orden:</strong> %s</p> <!-- Número de la orden -->
                          <p><strong>Observación:</strong> %s</p> <!-- Observación de la solicitud -->
                        </div>

                        %s
                        
                        <p>Si tienes alguna pregunta, contáctanos en <a href="mailto:pqr@saludmedcol.com">pqr@saludmedcol.com</a>.</p>
                        
                        <div class="footer">
                          <p>© 2024 Saludmedcol. Todos los derechos reservados.</p>
                          <p>Saludmedcol | Teléfono: (+57) 311 365 8659 - (+57) 311 319 6002 | <a href="mailto:pqr@saludmedcol.com">pqr@saludmedcol.com</a></p>
                        </div>
                      </div>
                    </body>

                    </html> 
                    """,
                titulo, userName, eff_date, orderNumber, details, partial ? partialDetails(totalPending, detailsPending): "");
    }



    private static String partialDetails(String totalPending, String detailsPending) {
      return String.format(
      """
        <h2>Estado del Envío</h2>
                        <p><strong>Medicamentos Pendientes:</strong> %s</p> <!-- Cantidad de medicamentos pendientes -->
                        <p><strong>Motivo:</strong> %s</p> <!-- Descripción de por qué no se enviaron -->
      """,totalPending, detailsPending);    }

}
