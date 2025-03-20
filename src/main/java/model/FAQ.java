package model;

import java.util.LinkedList;
import java.util.List;

public class FAQ {
    private final List<FAQSection> sections = new LinkedList<>();

    public void addSection(FAQSection section) {
        sections.add(section);
        section.setParent(null);
    }

    public List<FAQSection> getSections() {
        return sections;
    }
}
