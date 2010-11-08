import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.LogLevel;
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
  
  static {
    System.setProperty("java.awt.headless", "true");
  }
  
  private FileSet imageFiles;
  private String outFileName;
  
  public void addConfiguredFileSet(FileSet files) {
    if (imageFiles == null) {
      imageFiles = files;
    }
    else {
      throw new BuildException(getTaskName() + ": You can only specify one fileset.");
    }
  }
  
  public void setOutFile(String name) {
    outFileName = name;
  }
  
  public void execute() throws BuildException {
    
    PrintWriter out = null;
    
    try {
      out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));
      
      DirectoryScanner ds = imageFiles.getDirectoryScanner();
      String[] fileNames = ds.getIncludedFiles();
      
      for (String fileName : fileNames) {
        log("Processing " + fileName, LogLevel.VERBOSE.getLevel());
        BufferedImage image = ImageIO.read(new File(ds.getBasedir(), fileName));
        
        int height = image.getHeight();
        int width = image.getWidth();
        
        out.printf("%s,%d,%d%n", fileName, height, width);
      }
      
      log("Image size report written to " + outFileName);
      
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