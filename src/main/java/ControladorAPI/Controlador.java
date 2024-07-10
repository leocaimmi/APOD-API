package ControladorAPI;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfFileAttachmentAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfScreenAnnotation;

import com.itextpdf.kernel.pdf.filespec.PdfFileSpec;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import io.github.cdimascio.dotenv.Dotenv;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;


import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class Controlador {
    //atributos
    private final String APP_KEY = Dotenv.load().get("APP_KEY");//NASA KEY
    private final String BASE_URL = "https://api.nasa.gov/planetary/apod?api_key=" + APP_KEY;
    private APODClase apodClase;


    //constructores
    public Controlador() {
        apodClase = new APODClase();
    }
    //getters y setters

    public APODClase getApodClase() {
        return apodClase;
    }


    //metodos
    public boolean cargarCliente() throws IOException, JSONException {
        BufferedReader in = null;
        boolean rta = false;//"GET request failed: " + codigoRespuesta

        try
        {
            URL urlAPOD = new URL(BASE_URL.toString());//Instancio una URL que es de la info de la API
            HttpURLConnection urlConnection = (HttpURLConnection) urlAPOD.openConnection();//Armo la conexion http
            urlConnection.setRequestMethod("GET");//HTTP me permite hacer el get de la pagina

            int codigoRespuesta = urlConnection.getResponseCode();//codigo de respuesta
            if (codigoRespuesta == 200)//el 200 es codigo exitoso de la solicitud HTTP
            {
                in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));//para leer la respuesta de la conexión

                String inputLine;//leer cada línea de la respuesta

                StringBuffer contenido = new StringBuffer();//construir el contenido completo de la respuesta

                while ((inputLine = in.readLine()) != null)//Lee la respuesta línea por línea
                {
                    contenido.append(inputLine);
                }

                String contenidoJSON = contenido.toString();//una vez que tengo el contenido entero
                JSONObject jsonObject = new JSONObject(contenidoJSON);//agarro el JSON

                APODClase APOD = new APODClase();
                APOD.setTitle(jsonObject.getString("title"));
                APOD.setDate(jsonObject.getString("date"));
                APOD.setExplanation(jsonObject.getString("explanation"));
                //APOD.setHdurl(jsonObject.getString("hdurl"));
                APOD.setUrl(jsonObject.getString("url"));
                APOD.setMedia_type(jsonObject.getString("media_type"));
                APOD.setService_version(jsonObject.getString("service_version"));
                apodClase = APOD;
            }

        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (JSONException e) {
            throw e;
        } finally {
            try {
                in.close();// Cierra el BufferedReader
                rta = true;
            } catch (IOException e) {
                 throw e;
            }

        }
        return rta;
    }
    public void descargarVideoAPI()
    {
        String videoUrl = apodClase.getUrl(); // URL de la API
        String downloadPath = "src/main/resources/video.mp4"; // Ruta donde se descargará el video
        String ytdlpPath = "yt-dlp.exe"; // Ruta al ejecutable de yt-dlp
        if(apodClase.getMedia_type().equalsIgnoreCase("video"))
        {
            try {
                downloadVideo(ytdlpPath,videoUrl,downloadPath);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
    private void downloadVideo(String ytdlpPath, String videoUrl, String downloadPath) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(ytdlpPath, videoUrl, "-o", downloadPath);
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Leer la salida del proceso para ver los mensajes de yt-dlp
        try (Scanner scanner = new Scanner(process.getInputStream())) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Failed to download video. Exit code: " + exitCode);
        }
    }
    public void descargarImagenAPI() {
        try {
           if(apodClase.getMedia_type().equalsIgnoreCase("image"))
           {
            BufferedImage image = ImageIO.read(new URL(apodClase.getUrl()));//descargo la imagen desde la url que me da la API
            File outputFile = new File("src/main/resources" + File.separator + "location_NASA.jpg");
            // Sobrescribir la imagen si ya existe
            if (outputFile.exists()) {
                outputFile.delete();
            }
            ImageIO.write(image, "jpg", outputFile);
           }
//           else
//           {
//               System.out.println("SOY UN VIDEO");
//           }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String hacerPDFConRecursoAPI()
    {
        String rta = "";
        String rutaImagen = "src/main/resources/location_NASA.jpg";
        String rutaVideo = "src/main/resources/video.mp4";
        File recurso = null;
        try
        {

            // Crear el documento PDF
            PdfWriter pdfWriter = new  PdfWriter("TextoNASAAPI.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

            // Añadir el título
            document.add(new Paragraph(apodClase.getTitle()).setFontSize(16).setBold().setTextAlignment(TextAlignment.CENTER));

            // Añadir el contexto
            document.add(new Paragraph(apodClase.getExplanation()).setFontSize(12).setTextAlignment(TextAlignment.JUSTIFIED));

            // Añadir el fecha
            document.add(new Paragraph(apodClase.getDate()).setFontSize(12));
            // Verificar si la imagen existe o el video existe
            if(apodClase.getMedia_type().equalsIgnoreCase("image"))
            {
                recurso = new File(rutaImagen);
                // Añadir la imagen
                if (!recurso.exists())
                {
                    rta = "La imagen no existe en la ruta especificada: " + rutaImagen;
                }
                else
                {


                    ImageData imageData = ImageDataFactory.create(rutaImagen);
                    Image pdfImage = new Image(imageData);
                    // pdfImage.scaleToFit(550, 550);// Ajustar el tamaño de la imagen
                    pdfImage.setAutoScale(true).setHorizontalAlignment(HorizontalAlignment.CENTER);

                    document.add(pdfImage);
                    rta = "ruta correcta: "+rutaImagen;
                }
            }else if(apodClase.getMedia_type().equalsIgnoreCase("video"))
            {
                recurso = new File(rutaVideo);
                if (!recurso.exists())
                {
                    rta = "El video no existe en la ruta especificada: " + rutaImagen;
                }
                else
                {
                    // Adjuntar el archivo de video al PDF

                    PdfFileSpec fileSpec = PdfFileSpec.createEmbeddedFileSpec(pdfDocument, recurso.getAbsolutePath(), null, recurso.getName(), null,null);
                    PdfFileAttachmentAnnotation fileAttachment = new PdfFileAttachmentAnnotation(new Rectangle(175, 375, 200, 200), fileSpec);
                    fileAttachment.setColor(ColorConstants.BLUE);
                    fileAttachment.setTitle(new PdfString("Video adjunto"));
                    fileAttachment.setContents("Doble click para abrir el video");
                    pdfDocument.getLastPage().addAnnotation(fileAttachment);

                    rta = "ruta correcta: "+rutaImagen;
                }
            }



            // Cerrar el documento
            document.close();


        } catch (IOException e) {

            e.printStackTrace();
        }

        return rta;
    }

    @Override
    public String toString() {
        return "Controlador{" +
                "apodClase=" + apodClase +
                '}';
    }
}
