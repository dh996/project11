/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.83
 * Generated at: 2023-12-13 11:16:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.admin.include;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class versionup_005fstart_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1700193303370L));
    _jspx_dependants.put("jar:file:/C:/Dev/workspaces/project11/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/project11/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js\" integrity=\"sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\"></script>\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div id=\"upgogogo\">\r\n");
      out.write("      클릭\r\n");
      out.write("    </div>\r\n");
      out.write("    <div id=\"startConsole\" class=\"flex\">\r\n");
      out.write("        <div id=\"updateInformation\">\r\n");
      out.write("            <div id=\"nowUpdate\">\r\n");
      out.write("            </div><!-- #nowUpdate -->\r\n");
      out.write("            <div id=\"updateBar\">\r\n");
      out.write("            </div><!-- #updateBar -->\r\n");
      out.write("        </div><!-- #updateInformation -->\r\n");
      out.write("        <div id=\"updateRate\">\r\n");
      out.write("            <div id=\"updateCounts\"></div>\r\n");
      out.write("            <div id=\"updateRates\"></div>\r\n");
      out.write("        </div><!-- #updateRate -->\r\n");
      out.write("    </div><!-- #startConsole -->\r\n");
      out.write("    \r\n");
      out.write("    <script>\r\n");
      out.write("        var jQ = jQuery.noConflict();\r\n");
      out.write("        var version = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${version}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("        var keyData = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${keyData}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"\r\n");
      out.write("        var keyDataArray = keyData.replace(/[\\[\\]\"]+/g, '').split(', ');\r\n");
      out.write("        var updateCount = 1\r\n");
      out.write("        var checkUpdate = false;\r\n");
      out.write("        \r\n");
      out.write("        jQ(\"#upgogogo\").click(function(){\r\n");
      out.write("        	setChampUpdate(0);\r\n");
      out.write("        })\r\n");
      out.write("        \r\n");
      out.write("        function setChampUpdate(index) {\r\n");
      out.write("            if (index < keyData.length) {\r\n");
      out.write("                var champ = keyDataArray[index];\r\n");
      out.write("                var setChampUpdateTimeout;\r\n");
      out.write("\r\n");
      out.write("                jQ.ajax({\r\n");
      out.write("                    type: 'GET',\r\n");
      out.write("                    url: 'https://ddragon.leagueoflegends.com/cdn/' + version + '/data/ko_KR/champion/' + champ + '.json',\r\n");
      out.write("                    dataType: 'json',\r\n");
      out.write("                    success: function (rdata) {\r\n");
      out.write("                        data = rdata.data[champ];\r\n");
      out.write("                        showConsole(data, updateCount);\r\n");
      out.write("                        var champSkin = data.skins;\r\n");
      out.write("                    	var champImgCode = data.id;\r\n");
      out.write("                        //var imgCheck = updateChampImg(0, version, champSkin, champImgCode);\r\n");
      out.write("                        var dBCheck = updateChampDB(data, version);\r\n");
      out.write("                        updateCount++;\r\n");
      out.write("\r\n");
      out.write("                        setChampUpdateTimeout = setTimeout(function (dBCheck) {\r\n");
      out.write("                        	if(dBCheck===true){\r\n");
      out.write("                                function cancelSetChampUpdateTimeout() {\r\n");
      out.write("                                    clearTimeout(setChampUpdateTimeout);\r\n");
      out.write("                                }\r\n");
      out.write("                                cancelChampUpdateTimeout();\r\n");
      out.write("                                setChampUpdate(index + 1);\r\n");
      out.write("                        	}\r\n");
      out.write("                        }, 3000);\r\n");
      out.write("                    }\r\n");
      out.write("                });//if-ajax\r\n");
      out.write("            }else{\r\n");
      out.write("            	jQ('#updateBar').removeClass('on')\r\n");
      out.write("            	jQ('#startConsole').removeClass('on')\r\n");
      out.write("            	checkUpdate = false;\r\n");
      out.write("            	jQ.ajax({\r\n");
      out.write("                    url: \"/versionup.do\",\r\n");
      out.write("                    type: \"GET\",\r\n");
      out.write("                    success: function(response) {\r\n");
      out.write("                    	jQ(\"#result\").text(\"Response from server: \" + response);\r\n");
      out.write("                    },\r\n");
      out.write("                    error: function(error) {\r\n");
      out.write("                        console.log(\"Error:\", error);\r\n");
      out.write("                    }\r\n");
      out.write("                });//else-ajax\r\n");
      out.write("            }//if\r\n");
      out.write("        }//setChampUpdate\r\n");
      out.write("\r\n");
      out.write("        function showConsole(data, updateCount){\r\n");
      out.write("        	var upRate = (updateCount/keyData.length)*100\r\n");
      out.write("        	\r\n");
      out.write("        	jQ('#nowUpdate').text(data)\r\n");
      out.write("        	if(checkUpdate === false){\r\n");
      out.write("        		jQ('#updateBar').addClass('on')\r\n");
      out.write("        		jQ('#startConsole').addClass('on')\r\n");
      out.write("        	}\r\n");
      out.write("        	jQ('#updateCounts').text(updateCount+\"/\"+keyData.length)\r\n");
      out.write("        	jQ('#updateRates').text(upRate+\"%\")\r\n");
      out.write("        }//showConsole\r\n");
      out.write("        \r\n");
      out.write("        function updateChampDB(data, version){\r\n");
      out.write("        	\r\n");
      out.write("        	var sendDbData = {\r\n");
      out.write("        	        data: data,\r\n");
      out.write("        	        version: version\r\n");
      out.write("        	    };\r\n");
      out.write("        	\r\n");
      out.write("        	jQ.ajax({\r\n");
      out.write("                url: \"/versionup.upload\",\r\n");
      out.write("                type: \"POST\",\r\n");
      out.write("                contentType: \"application/json\",\r\n");
      out.write("                data: JSON.stringify(sendDbData),\r\n");
      out.write("                success: function(response) {\r\n");
      out.write("                    return true;\r\n");
      out.write("                },\r\n");
      out.write("                error: function(error) {\r\n");
      out.write("                    console.log(\"Error:\", error);\r\n");
      out.write("                    return false;\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("        }//updateChampDB\r\n");
      out.write("        \r\n");
      out.write("        //function updateChampImg(index, version, champSkin, champImgCode){\r\n");
      out.write("        //	if(index < champSkin.length){\r\n");
      out.write("        //		var champSkinCode = champSkin[index].num;\r\n");
      out.write("        //		var updateChampImgTimeout;\r\n");
      out.write("        //    	var sendImgData = {\r\n");
      out.write("        //    			champSkinCode: champSkinCode,\r\n");
      out.write("        //    			champImgCode: champImgCode,\r\n");
      out.write("        //    	        version: version\r\n");
      out.write("        //    	    };\r\n");
      out.write("        //    	\r\n");
      out.write("        //    	jQ.ajax({\r\n");
      out.write("        //    	    url: '/versionup.img',\r\n");
      out.write("        //    	    type: \"POST\",\r\n");
      out.write("        //            contentType: \"application/json\",\r\n");
      out.write("        //            data: JSON.stringify(sendImgData),\r\n");
      out.write("        //    	    success: function (response) {\r\n");
      out.write("        //    	    	if(index==0){\r\n");
      out.write("        //    	    		setConsoleImg(champImgCode);\r\n");
      out.write("        //    	    	}\r\n");
      out.write("        //    	    	updateChampImgTimeout = setTimeout(function () {\r\n");
      out.write("        //                    function cancelUpdateChampImgTimeout() {\r\n");
      out.write("        //                        clearTimeout(updateChampImgTimeout);\r\n");
      out.write("        //                    }\r\n");
      out.write("        //                    cancelUpdateChampImgTimeout();\r\n");
      out.write("        //                	updateChampImg(index + 1, version, champSkin, champImgCode);\r\n");
      out.write("        //                }, 3000);\r\n");
      out.write("        //    	    }\r\n");
      out.write("        //    	});\r\n");
      out.write("        //	}else{\r\n");
      out.write("        //		return true;\r\n");
      out.write("        //	}\r\n");
      out.write("        //}//updateChampImg\r\n");
      out.write("        \r\n");
      out.write("        //function setConsoleImg(champImgCode){\r\n");
      out.write("        //	var imageUrl = 'resources/img/'+champImgCode'_0.jpg';\r\n");
      out.write("        //	JQ('#startConsole').css('background-image', 'url(' + imageUrl + ')');\r\n");
      out.write("        //}//setConsoleImg\r\n");
      out.write("        //11.22:이제 데이터베이스에 저장하는거 만들고 css대충짠담에 메인페이지와 연결 지금까지하거 잗돌아가나 체크\r\n");
      out.write("      \r\n");
      out.write("    </script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}