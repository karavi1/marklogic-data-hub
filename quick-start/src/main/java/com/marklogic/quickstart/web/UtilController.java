package com.marklogic.quickstart.web;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marklogic.quickstart.model.SearchPathModel;
import com.marklogic.quickstart.util.FileUtil;

@Controller
@RequestMapping("/api/utils")
public class UtilController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilController.class);

	@RequestMapping(value = "/searchPath", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchPath(@RequestParam String path) {
		LOGGER.debug("Search Path:" + path);
		List<SearchPathModel> paths = new ArrayList<SearchPathModel>();
		String currentPath;

		if (StringUtils.isEmpty(path)) {
		    currentPath = "/";
			File[] roots = File.listRoots();
			for (int i = 0; i < roots.length; i++) {
				paths.add(new SearchPathModel(roots[i].getAbsolutePath(), roots[i].getAbsolutePath()));
			}
		}
		else {
		    currentPath = Paths.get(path).toAbsolutePath().normalize().toString();
			if (!path.equals("/")) {
				path = path + java.io.File.separator;
				Path parent = Paths.get(path).toAbsolutePath().normalize().getParent();
				if (parent != null) {
				    paths.add(new SearchPathModel(parent.toString(), ".."));
				}
			}

			List<String> folders = FileUtil.listDirectFolders(new File(path));
			for (String folder : folders) {
				String absPath = Paths.get(path, folder).toAbsolutePath().normalize().toString();
				paths.add(new SearchPathModel(absPath, folder));
			}
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("currentPath", currentPath);
		result.put("folders", paths);
		return result;
	}
}
