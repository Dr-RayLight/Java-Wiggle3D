import os
import sys
import torch
import cv2
import numpy as np

# 設定模型類型
model_type = "DPT_Large"  # 可選擇 "MiDaS_small" 或 "DPT_Hybrid" 或 "DPT_Large"
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

# 載入模型和配置
model = torch.hub.load("intel-isl/MiDaS", model_type).to(device).eval()
midas_transforms = torch.hub.load("intel-isl/MiDaS", "transforms")
transform = (
    midas_transforms.dpt_transform
    if "DPT" in model_type
    else midas_transforms.small_transform
)

# 讀取並預處理圖片
image_path = sys.argv[1]
print("Python Image Path:", image_path)

image = cv2.imread(image_path)
image_rgb = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
input_batch = transform(image_rgb).to(device)

# 生成深度圖
with torch.no_grad():
    prediction = model(input_batch)
prediction = (
    torch.nn.functional.interpolate(
        prediction.unsqueeze(1),
        size=image_rgb.shape[:2],
        mode="bicubic",
        align_corners=False,
    )
    .squeeze()
    .cpu()
    .numpy()
)

# 正規化深度圖並保存
depth_map = cv2.normalize(
    prediction, None, 0, 255, norm_type=cv2.NORM_MINMAX, dtype=cv2.CV_8U
)

folder = os.path.dirname(image_path)
output = os.path.join(folder, "output_depth_map.png")
cv2.imwrite(output, depth_map)

print("folder: ",folder, "\noutput_depth_map:",output)
print("Gen_Depth_Map_Success:",os.path.exists(output))