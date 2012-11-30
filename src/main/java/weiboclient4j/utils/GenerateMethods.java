package weiboclient4j.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class GenerateMethods {
    public static void main(String[] args) {
        String method = "public UserList getNearbyUsers";
        String[] requiredParams = new String[]{"Latitude", "Longitude"};
        String[] optionalParams = new String[]{"LocRange", "StartTime", "EndTime", "NearbySortType", "LocOffset", "Paging"};

        generateMethods(method, requiredParams, optionalParams);
    }

    private static void generateMethods(String method, String[] requiredParams, String[] optionalParams) {
        for (int mask = 0; mask < fn1(optionalParams.length); mask++) {
            System.out.println("// TODO " + mask);
            List<String> selectedOptionalParams = filterOptionalParams(optionalParams, mask);

            System.out.print(method + "(");

            for (int i = 0; i < requiredParams.length; i++) {
                printParam(requiredParams[i]);
                if (i < requiredParams.length - 1 || selectedOptionalParams.size() > 0) {
                    System.out.print(", ");
                }
            }

            for (int i = 0; i < selectedOptionalParams.size(); i++) {
                printParam(selectedOptionalParams.get(i));
                if (i < selectedOptionalParams.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println(") throws WeiboClientException {");
            System.out.println("    return null;");
            System.out.println("}");
        }
    }

    private static void printParam(String param) {
        System.out.print(param + " " + param.substring(0, 1).toLowerCase() + param.substring(1));
    }

    private static List<String> filterOptionalParams(String[] optionalParams, int mask) {
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < optionalParams.length; i++) {
            if (((1 << i) & mask) > 0) {
                result.add(optionalParams[i]);
            }
        }

        return result;
    }

    private static int fn1(int optionalParamsLength) {
        if (optionalParamsLength <= 1) {
            return 2;
        } else {
            return 2 * fn1(optionalParamsLength - 1);
        }
    }
}
