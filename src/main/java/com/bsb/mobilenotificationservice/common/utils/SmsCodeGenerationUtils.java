package com.bsb.mobilenotificationservice.common.utils;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmsCodeGenerationUtils {

  private static final int NUMBER_OF_DIGITS_IN_CODE = 4;
  private static final int[] DIGITS_ARRAY = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
  private static final char[] LETTERS_ARRAY = new char[]{'A', 'B', 'C', 'E', 'F', 'H',
      'J', 'K', 'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

  public String generateCode() {
    var random = new SecureRandom();

    var list = new LinkedList<String>();

    var letter = String.valueOf(LETTERS_ARRAY[random.nextInt(LETTERS_ARRAY.length)]);
    list.add(letter); // code should consist of 4 digits and 1 letter

    IntStream.range(0, NUMBER_OF_DIGITS_IN_CODE - 2)
        .mapToObj(i -> String.valueOf(DIGITS_ARRAY[random.nextInt(DIGITS_ARRAY.length)]))
        .forEach(list::add);
    Collections.shuffle(list);

    list.addFirst(String.valueOf(DIGITS_ARRAY[random.nextInt(DIGITS_ARRAY.length)]));
    list.addLast(String.valueOf(DIGITS_ARRAY[random.nextInt(DIGITS_ARRAY.length)]));

    return String.join("", list);
  }
}