package org.spongepowered.docs.tools.javadoc;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args) throws IOException {
        LOGGER.info("Javadoc-Checker-{}", Objects.toString(Main.class.getPackage().getImplementationVersion(), "DEV"));
        File docsSourceRoot;
        if (args.length >= 1) {
            docsSourceRoot = new File(args[0]);
        } else {
            // Executed from the tool project?
            docsSourceRoot = new File("../../source");
            if (!docsSourceRoot.exists()) {
                // Executed from the docs project?
                docsSourceRoot = new File("source");
                if (!docsSourceRoot.exists()) {
                    // Executed from the docs/source?
                    docsSourceRoot = new File(".");
                }
            }
        }
        if (!docsSourceRoot.exists()) {
            LOGGER.error("Docs Source not found at: ", docsSourceRoot.getAbsolutePath());
            throw new IllegalArgumentException("Docs Source not found at: " + docsSourceRoot.getAbsolutePath());
        }
        final boolean checkDeprecated;
        if (args.length >= 2) {
            checkDeprecated = Boolean.parseBoolean(args[1]);
        } else {
            checkDeprecated = false;
        }

        final File processingRoot = docsSourceRoot.getCanonicalFile();
        final int pathPrefixLength = processingRoot.getPath().length() + 1;
        LOGGER.info("Started {} on {}", Instant.now(), processingRoot.getAbsolutePath());

        final AtomicInteger fileCount = new AtomicInteger();
        final AtomicInteger warningCount = new AtomicInteger();
        final AtomicInteger errorCount = new AtomicInteger();

        recursiveFileStream(processingRoot)
                .map(file -> new FileChecker(file, pathPrefixLength, checkDeprecated))
                .forEachOrdered(checker -> {
                    fileCount.incrementAndGet();
                    try {
                        checker.process();
                    } catch (final Exception e) {
                        errorCount.incrementAndGet();
                        LOGGER.error("Failed to process file: {}", checker.getFileName(), e);
                    }
                    warningCount.addAndGet(checker.getFindingCount());
                });

        LOGGER.info("Completed {} - Files: {} - Warnings: {} - Errors: {}",
                Instant.now(), fileCount, warningCount, errorCount);

        if (errorCount.get() > 0) {
            System.exit(1);
        }
    }

    /**
     * Searches for all files with the <code>.rst</code> extension.
     *
     * @param file The file or directory to start the search.
     * @return The recursive stream that searches for all files with the
     *         <code>.rst</code> extension.
     */
    private static Stream<File> recursiveFileStream(final File file) {
        if (file.isDirectory()) {
            return Arrays.stream(file.listFiles()).flatMap(Main::recursiveFileStream);
        } else if (file.isFile() && file.getName().endsWith(".rst")) {
            return Stream.of(file);
        } else {
            return Stream.empty();
        }
    }

}
