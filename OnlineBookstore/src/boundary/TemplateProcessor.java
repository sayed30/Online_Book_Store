package boundary;

/* Bradley Reeves
 * 
 * 
 * A TemplateProcessor class used to process templater
 * that Dr. Mehdi created.
 * 
 */

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class TemplateProcessor {
	
	private String templateDir;
	private String templateName;
	private Configuration cfg;
	
	// Initializes all the values for the Configuration.
	
	public TemplateProcessor(String templateDir, ServletContext servletContext) {
		this.setTemplateDir(templateDir);
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setServletContextForTemplateLoading(servletContext, templateDir);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	}
	
	// Processes the template and loads the .ftl file.
	
	public void processTemplate(String templateName, SimpleHash root, HttpServletRequest request, HttpServletResponse response) {
		this.setTemplateName(templateName);
		Template template = null;
		try {
			template = cfg.getTemplate(templateName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Writer out = response.getWriter();
			response.setContentType("text/html");
			template.process(root, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public Configuration getCfg() {
		return cfg;
	}

	public void setCfg(Configuration cfg) {
		this.cfg = cfg;
	}

}
