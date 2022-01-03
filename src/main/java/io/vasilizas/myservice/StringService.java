package io.vasilizas.myservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StringService {

    public List<Integer> getNumbersListFromString(String str) {
        List<Integer> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        int start = 0;
        while (matcher.find(start)) {
            String value = str.substring(matcher.start(), matcher.end());
            int result = Integer.parseInt(value);
            list.add(result);
            start = matcher.end();
        }
        return list;
    }

    public List<Integer> getIdListFromNumberList(List<Integer> list) {
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                idList.add(list.get(i));
            }
        }
        return idList;
    }

    public List<Integer> getCountListFromNumberList(List<Integer> integers) {
        List<Integer> countList = new ArrayList<>();
        for (int i = 0; i < integers.size(); i++) {
            if (i % 2 != 0) {
                countList.add(integers.get(i));
            }
        }
        return countList;
    }
}
