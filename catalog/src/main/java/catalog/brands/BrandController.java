package catalog.brands;

import catalog.brands.application.BrandService;
import catalog.brands.application.dto.BrandCreateDto;
import catalog.brands.application.dto.BrandKeyValueDto;
import catalog.brands.application.dto.BrandListItemDto;
import catalog.brands.application.dto.BrandUpdateDto;
import io.swagger.annotations.Api;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "brands")
@RestController
@RequestMapping(value = "/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(value = "/")
    @ResponseBody
    public List<BrandListItemDto> getAll(@RequestParam int page, @RequestParam int pageSize) {
        final Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
        return brandService.getAll(pageable);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public List<BrandKeyValueDto> getAll() {
        return brandService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public BrandListItemDto getById(@PathVariable("id") Long id) {
        return brandService.getById(id);
    }

    @PostMapping(value = "/")
    public long create(@Validated @RequestBody BrandCreateDto brandCreateDto) {
        return brandService.create(brandCreateDto);
    }

    @PutMapping(value = "/")
    public void update(@Validated @RequestBody BrandUpdateDto brandUpdateDto) {
        brandService.update(brandUpdateDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
    }
}
