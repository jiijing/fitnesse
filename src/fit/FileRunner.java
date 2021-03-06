// Modified or written by Object Mentor, Inc. for inclusion with FitNesse.
// Copyright (c) 2002 Cunningham & Cunningham, Inc.
// Released under the terms of the GNU General Public License version 2 or later.
package fit;

// Copyright (c) 2002 Cunningham & Cunningham, Inc.
// Released under the terms of the GNU General Public License version 2 or later.

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class FileRunner {

  public String input;
  
  protected Parse tables;
  protected Dispatcher dispatcher = new Dispatcher();
  protected PrintWriter output;

  
  public static void main(String argv[]) {
    new FileRunner().run(argv);
  }

  public void run(String argv[]) {
    args(argv);
    process();
    exit();
  }

  public void process() {
    try {
      tables = new Parse(input);
      dispatcher.doTables(tables);
    } catch (Exception e) {
      exception(e);
    }
    tables.print(output);
  }

  public void args(String[] argv) {
    if (argv.length != 2) {
      System.err.println("usage: java fit.FileRunner input-file output-file");
      System.exit(-1);
    }
    File in = new File(argv[0]);
    File out = new File(argv[1]);
    dispatcher.summary.put("input file", in.getAbsolutePath());
    dispatcher.summary.put("input update", new Date(in.lastModified()));
    dispatcher.summary.put("output file", out.getAbsolutePath());
    try {
      input = read(in);
      output = new PrintWriter(new BufferedWriter(new FileWriter(out)));
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.exit(-1);
    }
  }

  protected String read(File input) throws IOException {
    char chars[] = new char[(int) (input.length())];
    FileReader in = new FileReader(input);
    in.read(chars);
    in.close();
    return new String(chars);
  }

  protected void exception(Exception e) {
    tables = new Parse("body", "Unable to parse input. Input ignored.", null, null);
    dispatcher.exception(tables, e);
  }

  protected void exit() {
    output.close();
    System.err.println(dispatcher.counts.toString());
    System.exit(dispatcher.counts.wrong + dispatcher.counts.exceptions);
  }

}
