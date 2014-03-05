    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    package liderexpress;

    /**
     *
     * @author Your Name <Edison Sanchez>
     *///import com.jacob.com.*;

    import java.io.*;
    import java.sql.*;

    import java.io.FileOutputStream;
    import java.util.Date;

    import com.itextpdf.text.Anchor;
    import com.itextpdf.text.BadElementException;
    import com.itextpdf.text.BaseColor;
    import com.itextpdf.text.Chapter;
    import com.itextpdf.text.Document;
    import com.itextpdf.text.DocumentException;
    import com.itextpdf.text.Element;
    import com.itextpdf.text.Font;
    import com.itextpdf.text.List;
    import com.itextpdf.text.ListItem;
    import com.itextpdf.text.Paragraph;
    import com.itextpdf.text.Phrase;
    import com.itextpdf.text.Section;
    import com.itextpdf.text.pdf.PdfPCell;
    import com.itextpdf.text.pdf.PdfPTable;
    import com.itextpdf.text.pdf.PdfWriter;
    import java.text.SimpleDateFormat;
    import java.util.Calendar;
    import javax.swing.JOptionPane;
    import static liderexpress.Cliente.connect;
    import static liderexpress.QueryLog.log;


    public class pdf {

      static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());  
      private static String FILE = "C:\\Users\\chupy\\Desktop\\reportes liderexpress\\reporte"+timeStamp+".pdf";            
     // File file = new File("C:\\Users\\chupy\\.ssh\\LiderExpress\\" + "reporte.pdf");
      private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
          Font.BOLD);
      private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
          Font.NORMAL, BaseColor.RED);
      private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
          Font.BOLD);
      private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
          Font.BOLD);
      static int op;

            public void newpdf(String id,int op) {
        try {
          Document document = new Document();
          PdfWriter.getInstance(document, new FileOutputStream(FILE));
          document.open();
          addMetaData(document,op);
          addTitlePage(document,op);
          addContent(document,id,op);
          System.out.println("setio");
          document.close();
          System.out.println("cerro");
          
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("ex");
        }


    }

      private static void addMetaData(Document document,int op) {
          switch (op) {
            case 1:  document.addTitle("Reporte de pagos por Cliente");
                     document.addSubject("");
                     document.addKeywords("");
                     document.addAuthor("Lider Express");
                     document.addCreator("Edinson Sanchez, Kevin Filella, Roberto Yoncon , Pedro Iñiguez");
                     break;
            case 2:  document.addTitle("Reporte de mercaderia por orden");
                     document.addSubject("");
                     document.addKeywords("");
                     document.addAuthor("Lider Express");
                     document.addCreator("Edinson Sanchez, Kevin Filella, Roberto Yoncon , Pedro Iñiguez");
                     break;
            case 3:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 4:  document.addTitle("Reporte de mercaderia por contenedor");
                     document.addSubject("");
                     document.addKeywords("");
                     document.addAuthor("Lider Express");
                     document.addCreator("Edinson Sanchez, Kevin Filella, Roberto Yoncon , Pedro Iñiguez");
                     break;
            case 5:  document.addTitle("Reporte de mercaderia por importacion");
                     document.addKeywords("");
                     document.addAuthor("Lider Express");
                     document.addCreator("Edinson Sanchez, Kevin Filella, Roberto Yoncon , Pedro Iñiguez");
                     break;
            case 6:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 7:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 8:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 9:  document.addTitle("Reporte de contenedores por importacion");
                     document.addKeywords("");
                     document.addAuthor("Lider Express");
                     document.addCreator("Edinson Sanchez, Kevin Filella, Roberto Yoncon , Pedro Iñiguez");
                     break;
            default: System.out.println("default1");
                     break;
        }
        
      }
      private static void addTitlePage(Document document,int op)
              
          throws DocumentException {
          Paragraph preface = new Paragraph();   
          switch (op) {
            case 1:  addEmptyLine(preface, 1);       
                     preface.add(new Paragraph("Reporte de Pagos por cliente", catFont));
                     addEmptyLine(preface, 1);       
                     preface.add(new Paragraph("Report generated by: Edinson Sanchez, Kevin Filella, Roberto Yoncon , Pedro Iñiguez  "+ new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                     smallBold));
                     addEmptyLine(preface, 3);
                     preface.add(new Paragraph("Este reporte presenta los pagos realizados por el cliente",
                     smallBold));
                     addEmptyLine(preface, 8);
                     document.add(preface);
                     document.newPage();
                     break;
            case 2:  addEmptyLine(preface, 1);       
                     preface.add(new Paragraph("Reporte de Mercaderia por orden", catFont));
                     addEmptyLine(preface, 1);       
                     preface.add(new Paragraph("Report generated by: Edinson Sanchez, Kevin Filella, Roberto Yoncon , Pedro Iñiguez  "+ new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                     smallBold));
                     addEmptyLine(preface, 3);
                     preface.add(new Paragraph("Este reporte presenta la mercaderia que contiene cada orden",
                     smallBold));
                     addEmptyLine(preface, 8);
                     document.add(preface);
                     document.newPage();
                     break;
            case 3:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 4:  addEmptyLine(preface, 1);       
                     preface.add(new Paragraph("Reporte de Mercaderia por contenedor", catFont));
                     addEmptyLine(preface, 1);       
                     preface.add(new Paragraph("Report generated by: Edinson Sanchez, Kevin Filella, Roberto Yoncon , Pedro Iñiguez  "+ new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                     smallBold));
                     addEmptyLine(preface, 3);
                     preface.add(new Paragraph("Este reporte presenta la mercaderia que contiene cada contenedor",
                     smallBold));
                     addEmptyLine(preface, 8);
                     document.add(preface);
                     document.newPage();
                     break;
            case 5:  addEmptyLine(preface, 1);       
                     preface.add(new Paragraph("Reporte de mercaderia por importacion", catFont));
                     addEmptyLine(preface, 1);       
                     preface.add(new Paragraph("Report generated by: Edinson Sanchez, Kevin Filella, Roberto Yoncon , Pedro Iñiguez  "+ new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                     smallBold));
                     addEmptyLine(preface, 3);
                     preface.add(new Paragraph("Este reporte presenta la mercaderia por cada importacion",
                     smallBold));
                     addEmptyLine(preface, 8);
                     document.add(preface);
                     document.newPage();
                     break;
            case 6:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 7:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 8:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 9:  addEmptyLine(preface, 1);       
                     preface.add(new Paragraph("Reporte de contenedores por importacion", catFont));
                     addEmptyLine(preface, 1);       
                     preface.add(new Paragraph("Report generated by: Edinson Sanchez, Kevin Filella, Roberto Yoncon , Pedro Iñiguez  "+ new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                     smallBold));
                     addEmptyLine(preface, 3);
                     preface.add(new Paragraph("Este reporte presenta los contenedores que se manejan en cada importacion",
                     smallBold));
                     addEmptyLine(preface, 8);
                     document.add(preface);
                     document.newPage();
                     break;
            default: System.out.println("default");
                     break;
        }
        
      }

      private static void addContent(Document document, String id, int op) throws DocumentException, SQLException {
        ResultSet rs = null; 
        switch (op) {
            case 1:  try {
                     System.out.println("ini");
                     Connection con=connect.Conexion_SQL();
                     Statement sentencia=con.createStatement();
                     String query="SELECT nombre from cliente where id_cliente="+id+";";
                     sentencia.execute(query);
                     System.out.println("midd");
                     rs = sentencia.getResultSet();
                     System.out.println("fin");
                     }catch(SQLException e){
                                            JOptionPane.showMessageDialog(null, "Error dato cliente no encontrado");
                                           }
                     System.out.println("a"); 
                     Anchor anchor = new Anchor("Cliente:", catFont);
                     anchor.setName("");                     
                     Chapter catPart = new Chapter(new Paragraph(anchor), 1);
                     rs.next();
                     Paragraph subPara = new Paragraph(rs.getString("nombre"), subFont);
                     Section subCatPart = catPart.addSection(subPara);
                     subCatPart.add(new Paragraph("Pagos"));        
                     createTable(subCatPart,id,op);
                     document.add(catPart);
                     document.add(catPart);
                     break;
            case 2:  try {
                     System.out.println("ini");
                     Connection con=connect.Conexion_SQL();
                     Statement sentencia=con.createStatement();
                     String query="SELECT id_orden from orden o  where o.id_cliente="+id+";";
                     sentencia.execute(query);
                     System.out.println("midd");
                     rs = sentencia.getResultSet();
                     System.out.println("fin");
                     }catch(SQLException e){
                                            JOptionPane.showMessageDialog(null, "Error dato orden no encontrado");
                                           }
                     System.out.println("a"); 
                     Anchor anchor2 = new Anchor("Orden:", catFont);
                     anchor2.setName("");                     
                     Chapter catPart2 = new Chapter(new Paragraph(anchor2), 1);
                     rs.next();
                     Paragraph subPara2 = new Paragraph(rs.getString("id_orden"), subFont);
                     Section subCatPart2 = catPart2.addSection(subPara2);
                     subCatPart2.add(new Paragraph("Mercaderia"));        
                     createTable(subCatPart2,id,op);
                     document.add(catPart2);
                     document.add(catPart2);
                     break;
            case 3:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 4:  try {
                     System.out.println("ini");
                     Connection con=connect.Conexion_SQL();
                     Statement sentencia=con.createStatement();
                     String query="SELECT id_merca from empaquetado where id_empaquetado="+id+";";
                     sentencia.execute(query);
                     System.out.println("midd");
                     rs = sentencia.getResultSet();
                     System.out.println("fin");
                     }catch(SQLException e){
                                            JOptionPane.showMessageDialog(null, "Error dato empaquetado no encontrado");
                                           }
                     System.out.println("a"); 
                     Anchor anchor4 = new Anchor("Empaquetado:", catFont);
                     anchor4.setName("");                     
                     Chapter catPart4 = new Chapter(new Paragraph(anchor4), 1);
                     rs.next();
                     Paragraph subPara4 = new Paragraph(rs.getString("id_empaquetado"), subFont);
                     Section subCatPart4 = catPart4.addSection(subPara4);
                     subCatPart4.add(new Paragraph("Contenedor"));        
                     createTable(subCatPart4,id,op);
                     document.add(catPart4);
                     document.add(catPart4);
                     break;
            case 5:  try {
                     System.out.println("ini");
                     Connection con=connect.Conexion_SQL();
                     Statement sentencia=con.createStatement();
                     String query="SELECT id_import from  importacion where id_import="+id+";";
                     sentencia.execute(query);
                     System.out.println("midd");
                     rs = sentencia.getResultSet();
                     System.out.println("fin");
                     }catch(SQLException e){
                                            JOptionPane.showMessageDialog(null, "Error dato importacion no encontrado");
                                           }
                     System.out.println("a"); 
                     Anchor anchor5 = new Anchor("Importacion:", catFont);
                     anchor5.setName("");                     
                     Chapter catPart5 = new Chapter(new Paragraph(anchor5), 1);
                     rs.next();
                     Paragraph subPara5 = new Paragraph(rs.getString("id_import"), subFont);
                     Section subCatPart5 = catPart5.addSection(subPara5);
                     subCatPart5.add(new Paragraph("Pagos"));        
                     createTable(subCatPart5,id,op);
                     document.add(catPart5);
                     document.add(catPart5);
                     break;
            case 6:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 7:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 8:  JOptionPane.showMessageDialog(null,"no hay reportes para esta entidad");
                     break;
            case 9:  try {
                     System.out.println("ini");
                     Connection con=connect.Conexion_SQL();
                     Statement sentencia=con.createStatement();
                     String query="SELECT id_contenedor from contenedor where id_contenedor="+id+";";
                     sentencia.execute(query);
                     System.out.println("midd");
                     rs = sentencia.getResultSet();
                     System.out.println("fin");
                     }catch(SQLException e){
                                            JOptionPane.showMessageDialog(null, "Error dato cliente no encontrado");
                                           }
                     System.out.println("a"); 
                     Anchor anchor9 = new Anchor("Contenedor:", catFont);
                     anchor9.setName("");                     
                     Chapter catPart9 = new Chapter(new Paragraph(anchor9), 1);
                     rs.next();
                     Paragraph subPara9 = new Paragraph(rs.getString("id_contenedor"), subFont);
                     Section subCatPart9 = catPart9.addSection(subPara9);
                     subCatPart9.add(new Paragraph("Importacion"));        
                     createTable(subCatPart9,id,op);
                     document.add(catPart9);
                     document.add(catPart9);
                     break;
            default: System.out.println("default2");
                     break;
        }
         

      }


      private static void createTable(Section subCatPart, String id,int op)
          throws BadElementException, SQLException {
        // ResultSet rs = null; 
         switch (op) {
            case 1:     PdfPTable table = new PdfPTable(6);
                        ResultSet rs = null; 
                        try {
                        System.out.println("ini");
                        Connection con=connect.Conexion_SQL();
                        Statement sentencia=con.createStatement();
                        String query="SELECT c.nombre,o.id_orden,f.id_factura,p.id_pago,p.cantidad,p.fecha from cliente c, orden o, factura2 f, pago2 p where p.id_factura=f.id_factura and c.id_cliente=o.id_cliente and o.id_orden=f.id_orden and c.id_cliente="+id+";";
                        sentencia.execute(query);
                        System.out.println("midd");
                        rs = sentencia.getResultSet();
                        System.out.println("fin");
                        }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Error dato cliente no encontrado");
                        }
                        PdfPCell c1 = new PdfPCell(new Phrase("Nombre"));
                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("ID Orden"));
                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("ID Factura"));
                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("ID Pago"));
                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("Valor"));
                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(c1);
                        c1 = new PdfPCell(new Phrase("Fecha Pago"));
                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(c1);
                        while(rs.next()){
                        table.setHeaderRows(1);
                        table.addCell(rs.getString("nombre"));
                        table.addCell(rs.getString("id_orden"));
                        table.addCell(rs.getString("id_factura"));
                        table.addCell(rs.getString("id_pago"));
                        table.addCell(rs.getString("cantidad"));
                        table.addCell(rs.getString("fecha"));      
                        }        
                        subCatPart.add(table);
                        break;
            case 2:     PdfPTable table2 = new PdfPTable(5);
                        ResultSet rs2 = null; 
                        try {
                        System.out.println("ini");
                        Connection con=connect.Conexion_SQL();
                        Statement sentencia=con.createStatement();
                        String query="SELECT id_merca, marca, cantidad, origen,precio_compra FROM liderexpress.mercaderia where id_orden="+id+";";
                        sentencia.execute(query);
                        System.out.println("midd");
                        rs2 = sentencia.getResultSet();
                        System.out.println("fin");
                        }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Error dato cliente no encontrado");
                        }
                        PdfPCell c12 = new PdfPCell(new Phrase("id_merca"));
                        c12.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table2.addCell(c12);
                        c12 = new PdfPCell(new Phrase("marca"));
                        c12.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table2.addCell(c12);
                        c12 = new PdfPCell(new Phrase("cantidad"));
                        c12.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table2.addCell(c12);
                        c12 = new PdfPCell(new Phrase("origen"));
                        c12.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table2.addCell(c12);
                        c12 = new PdfPCell(new Phrase("precio de compra"));
                        c12.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table2.addCell(c12);
                        
                        while(rs2.next()){
                        table2.setHeaderRows(1);
                        table2.addCell(rs2.getString("id_merca"));
                        table2.addCell(rs2.getString("marca"));
                        table2.addCell(rs2.getString("cantidad"));
                        table2.addCell(rs2.getString("origen"));
                        table2.addCell(rs2.getString("precio_compra"));
                              
                        }        
                        subCatPart.add(table2);                
                        break;
            case 4:     PdfPTable table4 = new PdfPTable(5);
                        ResultSet rs4 = null; 
                        try {
                        System.out.println("ini");
                        Connection con=connect.Conexion_SQL();
                        Statement sentencia=con.createStatement();
                        String query="SELECT  c.id_merca, marca, cantidad, origen,precio_compra FROM mercaderia c, empaquetado e, contenedor n  \n" +
                        "where id_empaquetado="+id+" and c.id_merca=e.id_merca and n.id_contenedor=e.id_contenedor;";
                        sentencia.execute(query);
                        System.out.println("midd");
                        rs4 = sentencia.getResultSet();
                        System.out.println("fin");
                        }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Error dato empaquetado no encontrado");
                        }
                        PdfPCell c14 = new PdfPCell(new Phrase("id_merca"));
                        c14.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table4.addCell(c14);
                        c14 = new PdfPCell(new Phrase("marca"));
                        c14.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table4.addCell(c14);
                        c14 = new PdfPCell(new Phrase("cantidad"));
                        c14.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table4.addCell(c14);
                        c14 = new PdfPCell(new Phrase("origen"));
                        c14.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table4.addCell(c14);
                        c14 = new PdfPCell(new Phrase("precio de compra"));
                        c14.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table4.addCell(c14);
                        
                        while(rs4.next()){
                        table4.setHeaderRows(1);
                        table4.addCell(rs4.getString("id_merca"));
                        table4.addCell(rs4.getString("marca"));
                        table4.addCell(rs4.getString("cantidad"));
                        table4.addCell(rs4.getString("origen"));
                        table4.addCell(rs4.getString("precio_compra"));
                              
                        }        
                        subCatPart.add(table4);     
                        break;
                
            case 5:     PdfPTable table5 = new PdfPTable(4);
                        ResultSet rs5 = null; 
                        try {
                        System.out.println("ini");
                        Connection con=connect.Conexion_SQL();
                        com.mysql.jdbc.CallableStatement pro = (com.mysql.jdbc.CallableStatement) con.prepareCall("{call factiMerca(?)}");
                        pro.setString(1,id);
                        pro.execute();
                        rs5=pro.getResultSet();
                        // rs.next();
                        System.out.println("fin");
                        }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Error dato iumportacion no encontrado");
                        }
                        PdfPCell c15 = new PdfPCell(new Phrase("estilo"));
                        c15.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table5.addCell(c15);
                        c15 = new PdfPCell(new Phrase("descuento"));
                        c15.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table5.addCell(c15);
                        c15 = new PdfPCell(new Phrase("cantidad"));
                        c15.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table5.addCell(c15);
                        c15 = new PdfPCell(new Phrase("precio_compra"));
                        c15.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table5.addCell(c15);
                        
                        while(rs5.next()){
                        table5.setHeaderRows(1);
                        table5.addCell(rs5.getString("estilo"));
                        table5.addCell(rs5.getString("dsc"));
                        table5.addCell(rs5.getString("cantidad"));
                        table5.addCell(rs5.getString("precio_compra"));
                                                     
                        }        
                        subCatPart.add(table5);   
                        break;
                
            case 9:     PdfPTable table9 = new PdfPTable(2);
                        ResultSet rs9 = null; 
                        try {
                        System.out.println("ini");
                        Connection con=connect.Conexion_SQL();
                        Statement sentencia=con.createStatement();
                        String query="SELECT i.id_import, c.id_contenedor FROM contenedor c, importacion i where i.id_import=c.id_import\n" +
                        "order by c.id_import ;";
                        sentencia.execute(query);
                        System.out.println("midd");
                        rs9 = sentencia.getResultSet();
                        System.out.println("fin");
                        }catch(SQLException e){
                        JOptionPane.showMessageDialog(null, "Error dato contenedor no encontrado");
                        }
                        PdfPCell c19 = new PdfPCell(new Phrase("id_import"));
                        c19.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table9.addCell(c19);
                        c19 = new PdfPCell(new Phrase("id_contenedor"));
                        c19.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table9.addCell(c19);               
                        while(rs9.next()){
                        table9.setHeaderRows(1);
                        table9.addCell(rs9.getString("id_import"));
                        table9.addCell(rs9.getString("id_contenedor"));
                                               
                        }        
                        subCatPart.add(table9); 
                        break;
            default:    System.out.println("default3");
                        break;
        }
      }

      private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
      }

      private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
          paragraph.add(new Paragraph(" "));
        }
      }
    } 

