package com.oxygenxml.relaxng.defaults;

import javax.xml.transform.sax.SAXSource;

import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;

import com.thaiopensource.relaxng.parse.Parseable;
import com.thaiopensource.relaxng.parse.compact.CompactParseable;
import com.thaiopensource.relaxng.pattern.AnnotationsImpl;
import com.thaiopensource.relaxng.pattern.CommentListImpl;
import com.thaiopensource.relaxng.pattern.NameClass;
import com.thaiopensource.relaxng.pattern.Pattern;
import com.thaiopensource.resolver.xml.sax.SAX;
import com.thaiopensource.resolver.xml.sax.SAXResolver;
import com.thaiopensource.util.PropertyMap;
import com.thaiopensource.util.VoidValue;
import com.thaiopensource.validate.SchemaReader;

/**
 * @author george@oxygenxml.com
 * 
 */
public class RNCDefaultValues extends RelaxNGDefaultValues {
  public RNCDefaultValues(EntityResolver resolver, ErrorHandler eh) {
    super(resolver, eh);
  }

  /**
   * class OxygenCompactSchemaReader extends SchemaReaderImpl
   */
  private static class OxygenCompactSchemaReader extends
      OxygenRelaxNGSchemaReader {
    /**
     * The instance of schema reader.
     */
    private static final SchemaReader theInstance = new OxygenCompactSchemaReader();

    /**
     * Not instantiable from outside.
     */
    private OxygenCompactSchemaReader() {
      super();
    }

    /**
     * Get the reader singleton.
     * 
     * @return The current Schema Reader instance.
     */
    private static SchemaReader getInstance() {
      return theInstance;
    }

    /**
     * Creates a parseable object from a catalog resolved input source
     * associated to a RNG schema.
     * 
     * @return the parseable object
     */
    @Override
    protected Parseable<Pattern, NameClass, Locator, VoidValue, CommentListImpl, AnnotationsImpl> createParseable(
        SAXSource source, SAXResolver saxResolver, ErrorHandler eh,
        PropertyMap properties) {
      return new CompactParseable<Pattern, NameClass, Locator, VoidValue, CommentListImpl, AnnotationsImpl>(
          SAX.createInput(source.getInputSource()), saxResolver.getResolver(),
          eh);
    }
  }

  /**
   * Returns an instance of OxygenCompactSchemaReader.
   */
  @Override
  protected SchemaReader getSchemaReader() {
    return OxygenCompactSchemaReader.getInstance();
  }
}