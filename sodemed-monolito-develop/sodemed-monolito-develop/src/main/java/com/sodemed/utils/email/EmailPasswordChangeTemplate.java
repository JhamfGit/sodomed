package com.sodemed.utils.email;

public class EmailPasswordChangeTemplate {

    public static String getTemplate(String userName, String newPassword) {
        return String.format("""
                <html>
                  <head>
                    <meta />
                    <meta content="width=device-width, initial-scale=1.0" />
                    <title>Cambio de Contraseña</title>
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
                    </style>
                  </head>
                  <div class="container">
                    <div class="header">
                      <h1>Cambio de Contraseña</h1>
                    </div>
                    <p>Hola <strong>%s</strong>,</p>
                    <p>Recibimos tu solicitud para cambiar la contraseña de tu cuenta.</p>
                    <p>Su nueva contraseña: <strong>%s</strong></p>
                    <div class="footer">
                      <p>© 2024 Saludmedcol. Todos los derechos reservados.</p>
                      <p> Saludmedcol | Teléfono: (+57) 311 365 8659 - (+57) 311 319 6002 | <a href="mailto:pqr@saludmedcol.com">pqr@saludmedcol.com</a></p>
                    </div>
                  </div>
                </html>
            """, userName, newPassword);
    }

}
