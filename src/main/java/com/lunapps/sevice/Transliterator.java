package com.lunapps.sevice;

import static java.lang.Character.toUpperCase;

public class Transliterator {

    public static String lat2cyr(String cyrWord) {
        StringBuilder sb = new StringBuilder(cyrWord.length());
        int i = 0;
        while (i < cyrWord.length()) {// Идем по строке слева направо. В принципе, подходит для обработки потока
            char ch = cyrWord.charAt(i);
            boolean lc = Character.isLowerCase(ch); // для сохранения регистра
            ch = toUpperCase(ch);// поднимаю регистр

            if (ch == 'Y' & (i + 1 < cyrWord.length())) {
                // Префиксная нотация вначале.
                // Проверяем, что символ первый, что с него начинается слово
                i++; // преходим ко второму символу сочетания
                ch = toUpperCase(cyrWord.charAt(i));
                switch (ch) {
                    case 'E':
                        sb.append(ch('Є', lc));
                        break;
                    case 'I':
                        if (i + 1 == cyrWord.length()) {
                            sb.append(ch('И', lc));
                            sb.append(ch('Й', lc));
                            break;
                        }
                        sb.append(ch('Ї', lc));
                        break;
                    case 'U':
                        sb.append(ch('Ю', lc));
                        break;
                    case 'A':
                        sb.append(ch('Я', lc));
                        break;
                    default: //проверяем первый ли это символ
                        if (i == 1) {
                            sb.append(ch('Й', lc));
                        } else {
                            sb.append(ch('И', lc));
                        }
                        i--;
                }
            }

            else if (ch == 'I' & i > 0 & (i + 1 < cyrWord.length())) {
                // Префиксная нотация вначале.
                // Если с искомого символа начинается слово.
                i++;
                // преходим ко второму символу сочетания
                ch = toUpperCase(cyrWord.charAt(i));
                switch (ch) {
                    case 'E':
                        sb.append(ch('Є', lc));
                        break;
                    case 'U':
                        sb.append(ch('Ю', lc));
                        break;
                    case 'A':
                        sb.append(ch('Я', lc));
                        break;
                    case 'I': // check for last symbol
                        if (i == cyrWord.length() - 1) {
                            sb.append(ch('І', lc));
                            sb.append(ch('Й', lc));
                            break;
                        }
                    default:
                        sb.append(ch('І', lc));
                        // если не встретили искомое совпадение, то возращаемся на одну последовательному поиску.
                        i--;
                }
            }

            else if (ch == 'S' & i >= 0 & (i + 1 < cyrWord.length())) { // Префиксная нотация вначале.  Если с искомого символа начинается слово.
                i++; // преходим ко второму символу сочетания
                ch = toUpperCase(cyrWord.charAt(i));
                switch (ch) {
                    case 'H':
                        if ((i != cyrWord.length() - 1)) {
                            if (toUpperCase(cyrWord.charAt(i + 1)) == 'C') {
                                if (toUpperCase(cyrWord.charAt(i + 2)) == 'H') {
                                    sb.append(ch('Щ', lc));
                                    i++;
                                    i++;
                                    break;
                                }
                            }
                        }
                        sb.append(ch('Ш', lc));
                        break;
                    default:
                        sb.append(ch('С', lc));

                        // если не встретили искомое совпадение, то возращаемся на одну последовательному поиску.
                        i--;
                }
            }

            else if (ch == 'C' & (i + 1 < cyrWord.length())) {
                // Префиксная нотация вначале.  Если с искомого символа начинается слово.
                i++; // преходим ко второму символу сочетания
                ch = toUpperCase(cyrWord.charAt(i));
                switch (ch) {
                    case 'H':
                        sb.append(ch('Ч', lc));
                        break;
                    default: // если не встретили искомое совпадение, то возращаемся на одну последовательному поиску.
                        i--;
                }
            }

            else if (ch == 'K' & (i + 1 < cyrWord.length())) { //
                i++; // преходим ко второму символу сочетания
                ch = toUpperCase(cyrWord.charAt(i));
                switch (ch) {
                    case 'H':
                        sb.append(ch('Х', lc));
                        break;
                    default: // если не встретили искомое совпадение, то возращаемся на одну последовательному поиску.
                        sb.append(ch('К', lc));
                        i--;
                }
            }

            else if (ch == 'T' & (i + 1 < cyrWord.length())) { //
                i++; // преходим ко второму символу сочетания
                ch = toUpperCase(cyrWord.charAt(i));
                switch (ch) {
                    case 'S':
                        sb.append(ch('Ц', lc));
                        break;
                    default: // если не встретили искомое совпадение, то возращаемся на одну последовательному поиску.
                        sb.append(ch('Т', lc));
                        i--;
                }
            }

            else if (ch == 'Z' & (i + 1 < cyrWord.length())) { //
                i++; // преходим ко второму символу сочетания
                ch = toUpperCase(cyrWord.charAt(i));
                switch (ch) {
                    case 'H':
                        sb.append(ch('Ж', lc));
                        break;
                    case 'G':
                        sb.append(ch('З', lc));
                        break;
                    default: // если не встретили искомое совпадение, то возращаемся на одну последовательному поиску.
                        sb.append(ch('З', lc));
                        i--;
                }
            }

            else {// одиночные символы
                switch (ch) {
                    case 'A':
                        sb.append(ch('А', lc));
                        break;
                    case 'B':
                        sb.append(ch('Б', lc));
                        break;
                    case 'V':
                        sb.append(ch('В', lc));
                        break;
                    case 'H':
                        sb.append(ch('Г', lc));
                        break;
                    case 'G':
                        sb.append(ch('Ґ', lc));
                        break;
                    case 'D':
                        sb.append(ch('Д', lc));
                        break;
                    case 'E':
                        sb.append(ch('Е', lc));
                        break;
                    case 'Z':
                        sb.append(ch('З', lc));
                        break;
                    case 'I':
                        sb.append(ch('І', lc));
                        break;
                    case 'Y':
                        sb.append(ch('И', lc));
                        break;
                    case 'K':
                        sb.append(ch('К', lc));
                        break;
                    case 'L':
                        sb.append(ch('Л', lc));
                        break;
                    case 'M':
                        sb.append(ch('М', lc));
                        break;
                    case 'N':
                        sb.append(ch('Н', lc));
                        break;
                    case 'O':
                        sb.append(ch('О', lc));
                        break;
                    case 'P':
                        sb.append(ch('П', lc));
                        break;
                    case 'R':
                        sb.append(ch('Р', lc));
                        break;
                    case 'S':
                        sb.append(ch('С', lc));
                        break;
                    case 'T':
                        sb.append(ch('Т', lc));
                        break;
                    case 'U':
                        sb.append(ch('У', lc));
                        break;
                    case 'F':
                        sb.append(ch('Ф', lc));
                        break;
                    default:
                        sb.append(ch(ch, lc));
                }
            }

            i++; // переходим к следующему символу
        }
        return sb.toString();
    }

    /**
     * Вспомогательная функция для восстановления регистра
     */

    private static char ch(char ch, boolean toLowerCase) {
        if (toLowerCase) return Character.toLowerCase(ch);
        else return ch;
    }

}
