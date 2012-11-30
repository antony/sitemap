includeTargets << grailsScript("_GrailsInit")

import org.codehaus.groovy.grails.web.mapping.*

target(main: "The description of the script goes here!") {

    ConfigObject sitemapConfig = new ConfigObject()
    sitemapConfig.sitemap = [:]

    grailsApplication.mainContext.getBean('org.grails.internal.URL_MAPPINGS_HOLDER').urlMappings.each { UrlMapping m ->
        if (m instanceof RegexUrlMapping) {

            String mappingString = m.urlPattern

            sitemapConfig.sitemap.put(mappingString, [:])

            m.urlData.tokens.eachWithIndex { token, index ->
                sitemapConfig.sitemap[mappingString][index] = []
            }
        }
    }

    StringWriter example = new StringWriter()
    sitemapConfig.writeTo example
    println example

}

setDefaultTarget(main)
