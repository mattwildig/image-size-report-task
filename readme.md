#Image Size Report Ant Task#

Generates a comma separated text file of filenames ant their dimensions, of the
format:

    <filename1>,<height>,<width>
    <filename2>,<height>,<width>

Build it like this:

    ant jar

Use it like this:

    <taskdef resource='antlib.xml' classpath="path/to/image-size-report.jar" />
    
    ...
    
    <target name="...">
      <generate-image-size-report outFile="whatever_you_want.txt">
          <fileset dir="..." />
            <includes ... />
          </fileset>
      </generate-image-size-report>
    </target>

The fileset is a normal ant fileset that should include all the image files you
want in the report.

Made in response to [this](http://stackoverflow.com/questions/4093130/ant-task-to-extract-image-dimensions-height-width-from-png-and-jpeg-files).