package ca.jrvs.apps.practice;

public interface RegexExc {
    /**
     * return true if filename extension is jpg or jpeg (case insensitive)
     * @param filename String that is assumed to be a jpg/jpeg filename
     * @return boolean indicating whether the filename is indeed jpg/jpeg filename
     */
    public boolean matchJpeg(String filename);

    /**
     * return true is ip is valid
     * to simpolify the problem, IP address range is from 0.0.0.0 to 999.999.999.999
     * @param ip String that is assumed to be an CIDR form IP address
     * @return boolean indicating whether the ip is indeed follows ip address format
     */
    public boolean matchIp(String ip);

    /**
     * return true if line is empty (e.g. empty, white space, tabs, etc...)
     * @param line String that is assumed to be an empty line
     * @return boolean indicating whether the line is an empty line
     */
    public boolean isEmptyLine(String line);
}