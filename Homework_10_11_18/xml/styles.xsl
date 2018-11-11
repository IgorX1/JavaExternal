<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <HTML>
        <BODY>
        <TABLE BORDER="2">
        <TR>
            <TH>ID</TH>
            <TH>Title</TH>
            <TH>Type</TH>
            <TH>Authorized</TH>
            <TH>Is Free</TH>
            <TH>Is Downloadable</TH>
            <TH>Has Email</TH>
        </TR>
        <xsl:for-each select="site/page">
            <TR>
                <TD><xsl:value-of select="@id"/></TD>
                <TD><xsl:value-of select="title"/></TD>
                <TD><xsl:value-of select="type"/></TD>
                <TD><xsl:value-of select="authorize"/></TD>
                <TD>
                    <xsl:if test="chars[@name='free']">
                        +
                    </xsl:if>
                </TD>
                <TD>
                    <xsl:if test="chars[@name='downloadable']">
                        +
                    </xsl:if>
                </TD>
                <TD>
                    <xsl:if test="chars[@name='hasEmail']">
                        +
                    </xsl:if>
                </TD>
            </TR>
        </xsl:for-each>
        </TABLE>
        </BODY>
        </HTML>
    </xsl:template>
</xsl:stylesheet>