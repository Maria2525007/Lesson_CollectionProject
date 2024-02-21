import java.util.*;
import java.util.stream.Collectors;

public class StudentSurnameStorage {
    private final TreeMap<String, Set<Long>> surnamesTreeMap = new TreeMap<>();

    public void studentCreated(Long id, String surname) {
        Set<Long> existingIds = surnamesTreeMap.getOrDefault(surname, new HashSet<>());
        existingIds.add(id);
        surnamesTreeMap.put(surname, existingIds);
    }

    public void studentDeleted(Long id, String surname) {
        surnamesTreeMap.get(surname).remove(id);
    }

    public void studentUpdated(Long id, String oldSurname, String newSurname) {
        studentDeleted(id, oldSurname);
        studentCreated(id, newSurname);
    }

    /**
     * Данный метод возвращает уникальные идентификаторы студентов,
     * чьи фамилии меньше или равны переданной по алфавиту
     *
     * @return set of long
     */
    public Set<Long> getSurnamesLessOrEqualThan(String surname) {
        return surnamesTreeMap.headMap(surname, true)
                .values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public void printAllSurnames() {
        System.out.println(surnamesTreeMap.keySet());
    }

    public String[] getSearchByCommand(String[] surnames) {
        String surname1 = surnames[0].trim();
        String surname2 = surnames[1].trim();
        if (surname1.compareTo(surname2) > 0) {
            String temp = surname1;
            surname1 = surname2;
            surname2 = temp;
        }
        return surnamesTreeMap.subMap(surname1, true, surname2, true)
                .keySet()
                .toArray(String[]::new);
    }


}
