package br.com.burgershack.android.util;

/*
 * Retirado de: https://raw.githubusercontent.com/jrvansuita/InscricoesBR/master/Mask.java
 *
 * Alterações realizadas por vitorrdgs(rodrigues.victor@outlook.com)
 *
 * O que foi mudado:
 * - Uso de StringBuilder para realizar formatações.
 * - Criado método para aplicar e definir parametros no EditText.
 * - Uso de apenas um Regex para unmask.
 * - Reaproveitamento do método mask para os eventos também.
 */

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Arrays;

/**
 * Created by jrvansuita on 17/11/15.
 */

public abstract class Mask {

    public static String unmask(String s) {
        return s.replaceAll("[-./(): ]", "");
    }

    public static String mask(MaskType type, String s) {
        String source = Mask.unmask(s);
        StringBuilder resultBuilder = new StringBuilder();

        int i = 0;
        for (char maskChar : type.getMask().toCharArray()) {
            if (maskChar != '#') {
                resultBuilder.append(maskChar);
            } else {
                try {
                    resultBuilder.append(source.charAt(i++));
                } catch (Exception e) {
                    break;
                }
            }
        }

        return resultBuilder.toString();
    }

    private static TextWatcher build(final MaskType type, final EditText edt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence sequence, int start, int before,
                                      int count) {

                if (isUpdating) {
                    isUpdating = false;
                    old = sequence.toString();
                    return;
                }

                if (!sequence.toString().isEmpty() && (sequence.toString().length() > old.length())) {
                    String result = mask(type, sequence.toString());

                    isUpdating = true;
                    edt.setText(result);
                    edt.setSelection(result.length());
                } else {
                    old = sequence.toString();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    public static void insert(final MaskType type, final EditText edt) {
        InputFilter[] filters = Arrays.copyOf(edt.getFilters(), edt.getFilters().length + 1);
        filters[filters.length - 1] = new InputFilter.LengthFilter(type.mask.length());
        edt.setFilters(filters);

        edt.setInputType(InputType.TYPE_CLASS_NUMBER);
        edt.setMaxLines(1);

        edt.addTextChangedListener(build(type, edt));
    }

    public enum MaskType {
        CEL("(##) #####-####"), // Telefone Celular
        CEP("#####-###"), // CEP
        CNPJ("##.###.###/####-##"), // CNPJ
        CPF("###.###.###-##"), // CPF
        DATE("##/##/####"), // DATA
        HOUR("##:##"), // HORA
        TEL("(##) ####-####"); // Telefone Residencial

        String mask;

        MaskType(String s) {
            mask = s;
        }

        public String getMask() {
            return mask;
        }
    }

}
