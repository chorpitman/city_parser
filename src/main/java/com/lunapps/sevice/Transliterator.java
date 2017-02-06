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

            if (ch == 'Y' & (i < cyrWord.length() - 1)) {
                // Префиксная нотация вначале.
                // Проверяем, что символ первый, что с него начинается слово
                i++; // преходим ко второму символу сочетания
                ch = toUpperCase(cyrWord.charAt(i));
                switch (ch) {
                    case 'E':
                        sb.append(ch('Є', lc));
                        break;
                    case 'I':
                        if (i == cyrWord.length() - 1) {
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
            } else if (ch == 'I' & i > 0 & (i < cyrWord.length() - 1)) {
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
            } else if (ch == 'S' & i >= 0 & (i < cyrWord.length() - 1)) { // Префиксная нотация вначале.  Если с искомого символа начинается слово.
                i++; // преходим ко второму символу сочетания
                ch = toUpperCase(cyrWord.charAt(i));
                switch (ch) {
                    case 'H':
                        if ((i != cyrWord.length() - 1)) {
                            //следующий элемент в массива
                            if (toUpperCase(cyrWord.charAt(i + 1)) == 'C') {
                                //следующий элемент в массива
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
            } else if (ch == 'C' & (i < cyrWord.length() - 1)) {
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
            } else if (ch == 'K' & (i < cyrWord.length() - 1)) { //
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
            } else if (ch == 'T' & (i < cyrWord.length() - 1)) { //
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
            } else if (ch == 'Z' & (i < cyrWord.length() - 1)) { //
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
            } else if (ch == 'A' & (i < cyrWord.length() - 1)) { //
                i++; // преходим ко второму символу сочетания
                ch = toUpperCase(cyrWord.charAt(i));
                switch (ch) {
                    case 'I':
                        sb.append(ch('А', lc));
                        sb.append(ch('Й', lc));
                        break;
                    default:
                        // если не встретили искомое совпадение, то возращаемся на одну последовательному поиску.
                        sb.append(ch('А', lc));
                        i--;
                }
            }

//            else if (i + 1 < cyrWord.length() && toUpperCase(cyrWord.charAt(i + 1)) == 'H') {// Постфиксная нотация, требует информации о двух следующих символах. Для потока придется сделать обертку с очередью из трех символов.
//                switch (ch) {
//                    case 'Z':
//                        sb.append(ch('Ж', lc));
//                        break;
//                    case 'K':
//                        sb.append(ch('Х', lc));
//                        break;
//                    case 'C':
//                        sb.append(ch('Ч', lc));
//                        break;
//                    case 'S':
//                        if (i + 2 < cyrWord.length() && toUpperCase(cyrWord.charAt(i + 2)) == 'H') { // проверка на двойной постфикс
//                            sb.append(ch('Щ', lc));
//                            i++; // пропускаем первый постфикс
//                        } else {
//                            sb.append(ch('Ш', lc));
//                        }
//                        break;
//                    case 'E':
//                        sb.append(ch('Э', lc));
//                        break;
//                    case 'I':
//                        sb.append(ch('Ы', lc));
//                        break;
//                    default:
//                        throw new IllegalArgumentException("Illegal transliterated symbol '" + ch + "' at position " + i);
//                }
//                i++; // пропускаем постфикс
//            }
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
