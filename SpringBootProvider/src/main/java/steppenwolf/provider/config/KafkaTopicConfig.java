package steppenwolf.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){

        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // Configuracion del tratamiento de borrado de mensajes, delete (borra el mensaje despues de cierto tiempo), compact (mantiene siempre el mas actual)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // Cuanto es el tiempo que se van a retener los mensajes, cuanto van a durar en el topic, "86400000" un dia en milisegundos
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); //Tamano maximo de los segmentos dentro del topic, en bytes, 1073741824 un gigabyte (por defecto 1GB)
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); // Tamano maximo de cada mensaje defecto 1MB

        return TopicBuilder.name("nuevo-topic")
                .partitions(2)
                .replicas(1)
                .configs(configurations)
                .build();
    }
}
