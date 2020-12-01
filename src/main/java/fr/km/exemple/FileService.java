package fr.km.exemple;



import org.jooq.lambda.Unchecked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileService {

}

interface OrderRepo extends JpaRepository<Order, Integer> {
    Stream<Order> findByActiveTrue();
}

//abstract class  FileExporter{
//
//    public File exportFile(String fileName) {
//        File file = new File("export/" + fileName);
//        try (Writer writer = new FileWriter(file)) {
//            writeContent(writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
//    protected abstract void writeContent(Writer writer) throws IOException;
//
//}
//
//class OrderExporter extends FileExporter{
//    private OrderRepo repo;
//    protected void writeContent(Writer writer) throws IOException {
//        writer.write("OderId;Date\n");
//        repo.findByActiveTrue()
//                .map(o -> o.getId() + ";" + o.getCreationDate())
//                .forEach(t -> {
//                    try {
//                        writer.write(t);
//                    } catch (IOException io) {
//                        throw new RuntimeException();
//                    }
//                });
//    }
//}
//
//class UserExporter extends FileExporter{
//    protected void writeContent(Writer writer) throws IOException {
//        // write here user write content
//    }
//}

    /**
     * SoLUTION
     * delete extends OrderExporter and UserExporter by introducing Consumer<Writer> class
     * because writeContent method is void
     *
     **/
class  FileExporter{
    public File exportFile(String fileName, Consumer<Writer> contentWriter) {
        File file = new File("export/" + fileName);
        try (Writer writer = new FileWriter(file)) {
            contentWriter.accept(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

        public static void main(String[] args) {
            FileExporter fileExporter = new FileExporter();
            OrderExporterWriter orderExporterWriter = new OrderExporterWriter();
            UserExporterWriter userExporterWriter = new UserExporterWriter();
            fileExporter.exportFile("orders.txt", Unchecked.consumer(orderExporterWriter::writeContent));
            fileExporter.exportFile("users.txt", Unchecked.consumer(userExporterWriter::writeContent));
        }
}

class OrderExporterWriter {
    private OrderRepo repo;
    public void writeContent(Writer writer) throws IOException {
        writer.write("OderId;Date\n");
        repo.findByActiveTrue()
                .map(o -> o.getId() + ";" + o.getCreationDate())
                .forEach(Unchecked.consumer(writer::write));
    }
}

class UserExporterWriter {
    public void writeContent(Writer writer) throws IOException {
        // write here user write content
    }
}