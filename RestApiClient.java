import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class RestApiClientWithParsing {
    public static void main(String[] args) {
        try {
            String apiUrl =
"https://api.open-meteo.com/v1/forecast?latitude=17.68&longitude=83.21&current_weather=true";
             URL url = new URL(apiUrl);
             HttpURLConnection connection =
             (HttpURLConnection) 
url.openConnection();
             connection.setRequestMethod("GET");
             BufferedReader reader = 
             new BufferedReader(
             new 
InputStreamReader(connection.getInputStream()));
             StringBuilder response=new 
StringBuilder();
             String line;
             while ((line = reader.readLine()) !=
null) {
                response.append(line);
             }            
             reader.close();
             String json = response.toString();
             int start =
json.indexOf("\"current_weather\"");
             String currentWeather =
             json.substring(start);
             String temperature =  
             extractValue(currentWeather,
            "\"temperature\":",  
            ",");
             String windspeed = 
             extractValue(currentWeather,"\"windspeed\":",
              ",");
             String time = 
                     extractValue(currentWeather, 
                     "\"time\":\"", 
                     "\"");
             System.out.println("===== Weather Report =====");
             System.out.println("Temperature :" + temperature +" C ");
             System.out.println("Wind Speed : " + windspeed +"km/h");
             System.out.println("Time    : " + time);
            } catch (Exception e) {
                System.out.println("Error occured:");
                e.printStackTrace();
            }
        }
        public static String extractValue(String 
text, 
                                          String 
        startKey,                         
                                          String 
        endChar) {
            int start = text.indexOf(startKey);
            if (start == -1) {
                return "Not Found";
            }
            start = start + startKey.length();
            int end = text.indexOf(endChar, start);
        return text.substring(start, end);
            }
        }
