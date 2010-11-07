import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageSizeTask extends Task{
  
  private ArrayList<FileSet> fileSets = new ArrayList<FileSet>();
  private String outFileName;
  
  public void addConfiguredFileSet(FileSet imageFiles) {
    fileSets.add(imageFiles);
  }
  
  public void setOutFile(String name) {
    outFileName = name;
  }
  
  public void execute() throws BuildException {
    
    PrintWriter out = null;
    
    try {
      out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
      
      for (FileSet fileSet : fileSets) {
        DirectoryScanner ds = fileSet.getDirectoryScanner();
        String[] fileNames = ds.getIncludedFiles();
        
        for (String fileName : fileNames) {
          BufferedImage image = ImageIO.read(new File(ds.getBasedir(), fileName));
          
          int height = image.getHeight();
          int width = image.getWidth();
          
          out.printf("%s,%d,%d%n", fileName, height, width);
        }
      }
      
    }
    catch (IOException e) {
      throw new BuildException(e);
    }
    finally {
      if (out != null) {
        out.close();
      }
    }
  }
  
}