package br.com.caelum.vraptor.social;

import br.com.caelum.vraptor.social.twitter.TwitterProfile;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import net.vidageek.mirror.dsl.Mirror;

public class SocialIntegrationReader<T> {

    private XStream xs;

    {
        xs = new XStream() {
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
        };
        xs.processAnnotations(TwitterProfile.class);
    }

    public T from(String xml) {
        return (T) xs.fromXML(xml);
    }
}
