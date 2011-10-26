package br.com.caelum.vraptor.social;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.social.facebook.FacebookProfile;
import br.com.caelum.vraptor.social.twitter.TwitterProfile;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import net.vidageek.mirror.dsl.Mirror;

import java.io.Writer;

@Component
@ApplicationScoped
public class SocialIntegrationReader {

    private XStream xs;

    {
        xs = new XStream(new JettisonMappedXmlDriver()) {
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (new Mirror().on(definedIn).reflect().field(fieldName) != null) {
                            return true;
                        }
                        return false;
                    }
                };
            }

            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }

        };
        xs.autodetectAnnotations(true);
    }

    //assuming that every json comes without root
    public <T> T from(String jsonWithoutRoot,Class<T> root) {
        String jsonWrappedWithRoot = String.format("{\"%s\":%s}",root.getName(),jsonWithoutRoot);
        return (T) xs.fromXML(jsonWrappedWithRoot);
    }
}
