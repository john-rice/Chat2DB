
package ai.chat2db.server.admin.api.controller.team;

import ai.chat2db.server.admin.api.controller.team.converter.TeamAdminConverter;
import ai.chat2db.server.common.api.controller.request.CommonPageQueryRequest;
import ai.chat2db.server.admin.api.controller.team.request.TeamCreateRequest;
import ai.chat2db.server.admin.api.controller.team.request.TeamUpdateRequest;
import ai.chat2db.server.admin.api.controller.team.vo.TeamPageQueryVO;
import ai.chat2db.server.domain.api.service.TeamService;
import ai.chat2db.server.tools.base.wrapper.result.ActionResult;
import ai.chat2db.server.tools.base.wrapper.result.DataResult;
import ai.chat2db.server.tools.base.wrapper.result.web.WebPageResult;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Team Management
 *
 * @author Jiaju Zhuang
 */
@RequestMapping("/api/admin/team")
@RestController
public class TeamAdminController {
    @Resource
    private TeamService teamService;
    @Resource
    private TeamAdminConverter teamAdminConverter;

    /**
     * Pagination query
     *
     * @param request
     * @return
     * @version 2.1.0
     */
    @GetMapping("/page")
    public WebPageResult<TeamPageQueryVO> page(@Valid CommonPageQueryRequest request) {
        return teamService.pageQuery(teamAdminConverter.request2param(request), null)
            .mapToWeb(teamAdminConverter::dto2vo);
    }

    /**
     * create
     *
     * @param request
     * @return
     * @version 2.1.0
     */
    @PostMapping("/create")
    public DataResult<Long> create(@RequestBody TeamCreateRequest request) {
        return teamService.create(teamAdminConverter.request2param(request));
    }

    /**
     * update
     *
     * @param request
     * @return
     * @version 2.1.0
     */
    @PostMapping("/update")
    public DataResult<Long> update(@RequestBody TeamUpdateRequest request) {
        return teamService.update(teamAdminConverter.request2param(request));
    }

    /**
     * delete
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ActionResult delete(@PathVariable Long id) {
        return teamService.delete(id);
    }
}
