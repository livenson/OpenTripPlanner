package org.opentripplanner.gtfs;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class CompetentAuthorityTable {
    private static final Logger LOG = LoggerFactory.getLogger(CompetentAuthorityTable.class);

    private Map<String, String> authorities = Maps.newHashMap();

    public CompetentAuthorityTable (String name) {
        //InputStream is = CompetentAuthorityTable.class.getClassLoader().getResourceAsStream(name);

        File file = new File(name);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                final String[] parts = line.split("\\s*,\\s*");
                authorities.put(parts[0], parts[1]);
            }
        } catch (FileNotFoundException e) {
            LOG.error("File not found: " + name);
            e.printStackTrace();
        }

    }

    public Map<String, String> getAuthorities() {
        return authorities;
    }
}
