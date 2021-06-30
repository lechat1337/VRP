
public class DomParserDemo {

   public static void main(String[] args) {
      VehicleRoutingImporter importer = new VehicleRoutingImporter();
      VehicleRoutingSolution lol = importer.readSolution("C:/Users/aleksejs/coursera/examples/sources/data/vehiclerouting/solved/test-cvrp-couresera.xml");
      System.out.println(lol.venchileList.item(0));
   }
}